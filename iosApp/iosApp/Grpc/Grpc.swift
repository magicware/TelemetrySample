//
//  GrpcFactory.swift
//  iosApp
//
//  Created by mw on 20.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import GRPC
import NIO
import SwiftProtobuf
import ComposeApp

class GrpcFactory : GrpcClientFactory {

    func create(uri: UriUri, token: String) throws -> ClientGrpcPlatformClient {
        return try GrpcSwiftClient(host: uri.host ?? "", port: 443, token: token)
    }
    
}

class GetImageFileResponseFlowImp : ClientGetImageFileResponseFlow {
    
    var stream: GRPCAsyncResponseStream<Profilog_Records_Images_GetImageFileResponse>.Iterator
    
    init(
        channel: GRPCChannel,
        calllOptions: CallOptions,
        request: ClientGetImageFileRequest
    ) throws {
        self.stream = Profilog_Records_Images_RecordsImagesGrpcServiceAsyncClient(
            channel: channel,
            defaultCallOptions: calllOptions
        ).getImage(
            try Profilog_Records_Images_GetImageFileRequest(
                contiguousBytes: request.encode().toNsData()
            )
        ).makeAsyncIterator()
    }
    
    override func __getValue() async throws -> ClientGetImageFileResponse? {
        return try await stream.next()?.toKotlinModel(adapter: ClientGetImageFileResponse.companion.ADAPTER)
    }
}

public class GrpcSwiftClient : ClientGrpcPlatformClient {
    
    private let channel: GRPCChannel
    private let group: MultiThreadedEventLoopGroup
    private let callOptions: CallOptions
    
    public init(host: String, port: Int, token: String) throws {
        self.group = MultiThreadedEventLoopGroup(numberOfThreads: 1)
        
        self.channel = try GRPCChannelPool.with(
            target: .host(host, port: port),
            transportSecurity: .tls(.makeClientConfigurationBackedByNIOSSL()),
            eventLoopGroup: group
        ) { c in
            c.maximumReceiveMessageLength = Int.max
        }
        
        self.callOptions = CallOptions(
            customMetadata: .init([("Authorization", "Bearer \(token)")])
        )
    }

    public func __aggregation(request: ClientGetRecordsRequest) async throws -> ClientGetRecordsResponse {
        let rq = try Profilog_Records_GetRecordsRequest(
            contiguousBytes: request.encode().toNsData()
        )
        let client = Profilog_Records_RecordsGrpcServiceAsyncClient(
            channel: self.channel,
            defaultCallOptions: self.callOptions)
        let response = try await client.getRecords(rq)
        
        return try response.toKotlinModel(adapter: ClientGetRecordsResponse.companion.ADAPTER)
    }
    
    public func __records(request: ClientGetRawRecordsRequest) async throws -> ClientGetRawRecordsResponse {
        let rq = try Profilog_Records_V2_GetRawRecordsRequest(
            contiguousBytes: request.encode().toNsData()
        )
        let client = Profilog_Records_V2_RecordsGrpcServiceAsyncClient(
            channel: self.channel,
            defaultCallOptions: self.callOptions)
        let response = try await client.getRawRecords(rq)
        
        return try response.toKotlinModel(adapter: ClientGetRawRecordsResponse.companion.ADAPTER)
    }
    
    
    
    public func getImageFile(request: ClientGetImageFileRequest) throws -> ClientGetImageFileResponseFlow {
        return try GetImageFileResponseFlowImp(
            channel: self.channel,
            calllOptions: self.callOptions,
            request: request
        )
    }
    
    public func __synchronize(request: ClientSynchronizeRecordsRequest) async throws -> ClientSynchronizeRecordsResponse {
        let rq = try Profilog_Records_V2_SynchronizeRecordsRequest(
            contiguousBytes: request.encode().toNsData()
        )
        let client = Profilog_Records_V2_RecordsGrpcServiceAsyncClient(
            channel: self.channel,
            defaultCallOptions: self.callOptions
        )
        let response = try await client.synchronizeRecords(rq)
        
        return try response.toKotlinModel(adapter: ClientSynchronizeRecordsResponse.companion.ADAPTER)
    }
    
    
    public func __uploadImageFile(request: SkieSwiftFlow<ClientImageFileChunk>) async throws {
        let client = Profilog_Records_Images_RecordsImagesGrpcServiceAsyncClient(
            channel: self.channel,
            defaultCallOptions: self.callOptions
        )
        
        
        try await client.uploadImage(request.map({ chunk in
            try Profilog_Records_Images_ImageFileChunk(contiguousBytes: chunk.encode().toNsData())
        }))
    }
    
    public func close() {
        channel.close()
        do {
            try group.syncShutdownGracefully()
        } catch {
            
        }
    }
    
}

extension SwiftProtobuf.Message {
    func toKotlinModel<WireMessage, Adapter: Wire_runtimeProtoAdapter<WireMessage>>(adapter: Adapter) throws -> WireMessage {
        let data = try self.serializedData()
        let result = adapter.decode(bytes: data.toKotlinByteArray())
        return try result ?? { throw fatalError("invalid byte conversion")}()
    }
}

extension Foundation.Data {
    func toKotlinByteArray() -> KotlinByteArray {
        let nsData = NSData(data: self)
        
        return KotlinByteArray(size: Int32(self.count)) { index -> KotlinByte in
            let byte = nsData.bytes.load(fromByteOffset: Int(truncating: index), as: Int8.self)
            return KotlinByte(value: byte)
        }
    }
}
