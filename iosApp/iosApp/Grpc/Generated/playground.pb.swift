// DO NOT EDIT.
// swift-format-ignore-file
//
// Generated by the Swift generator plugin for the protocol buffer compiler.
// Source: playground.proto
//
// For information on using the generated types, please see the documentation:
//   https://github.com/apple/swift-protobuf/

import Foundation
import SwiftProtobuf

// If the compiler emits an error on this type, it is because this file
// was generated by a version of the `protoc` Swift plug-in that is
// incompatible with the version of SwiftProtobuf to which you are linking.
// Please ensure that you are building against the same version of the API
// that was used to generate this file.
fileprivate struct _GeneratedWithProtocGenSwiftVersion: SwiftProtobuf.ProtobufAPIVersionCheck {
  struct _2: SwiftProtobuf.ProtobufAPIVersion_2 {}
  typealias Version = _2
}

struct Profilog_Playground_GenerateNumbersRequest {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// kolik chci vyprodukovat čísel
  var count: Int32 = 0

  /// jaká pauza bude mezi jednotlivými emitacemi (milisekundy)
  var delay: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_GenerateNumbersResponse {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// číslo
  var number: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_StoreNumbersRequest {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// číslo, které chci "uložit"
  var number: Int32 = 0

  /// jak dlouho by serveru mělo trvat to uložení (milisekundy)
  var delay: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_StoreNumbersResponse {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// pokud poslední ukládané číslo bylo sudé, pak Ano, jinak Ne
  var success: Bool = false

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_StoreAndReturnNumbersRequest {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// číslo, které chci "uložit"
  var number: Int32 = 0

  /// jak dlouho by serveru mělo trvat to uložení (milisekundy)
  var delay: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_StoreAndReturnNumbersResponse {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// číslo, které bylo "uloženo"
  var number: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_StoreAndReturnNumbersRequest2 {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// číslo, které chci "uložit"
  var number: Int32 = 0

  /// jak dlouho by serveru mělo trvat to uložení (milisekundy)
  var delay: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_StoreAndReturnNumbersResponse2 {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// součet čísel z poslední {count} chunků vstupního streamu, které byly "uloženy"
  var sum: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_StoreAndReturnNumbersRequest3 {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// čísla, které chci "uložit"
  var number1: Int32 = 0

  var number2: Int32 = 0

  var number3: Int32 = 0

  /// jak dlouho by serveru mělo trvat to uložení (milisekundy) jednoho čísla
  var delay: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

struct Profilog_Playground_StoreAndReturnNumbersResponse3 {
  // SwiftProtobuf.Message conformance is added in an extension below. See the
  // `Message` and `Message+*Additions` files in the SwiftProtobuf library for
  // methods supported on all messages.

  /// poslední "uložené" číslo
  var number: Int32 = 0

  var unknownFields = SwiftProtobuf.UnknownStorage()

  init() {}
}

#if swift(>=5.5) && canImport(_Concurrency)
extension Profilog_Playground_GenerateNumbersRequest: @unchecked Sendable {}
extension Profilog_Playground_GenerateNumbersResponse: @unchecked Sendable {}
extension Profilog_Playground_StoreNumbersRequest: @unchecked Sendable {}
extension Profilog_Playground_StoreNumbersResponse: @unchecked Sendable {}
extension Profilog_Playground_StoreAndReturnNumbersRequest: @unchecked Sendable {}
extension Profilog_Playground_StoreAndReturnNumbersResponse: @unchecked Sendable {}
extension Profilog_Playground_StoreAndReturnNumbersRequest2: @unchecked Sendable {}
extension Profilog_Playground_StoreAndReturnNumbersResponse2: @unchecked Sendable {}
extension Profilog_Playground_StoreAndReturnNumbersRequest3: @unchecked Sendable {}
extension Profilog_Playground_StoreAndReturnNumbersResponse3: @unchecked Sendable {}
#endif  // swift(>=5.5) && canImport(_Concurrency)

// MARK: - Code below here is support for the SwiftProtobuf runtime.

fileprivate let _protobuf_package = "profilog.playground"

extension Profilog_Playground_GenerateNumbersRequest: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".GenerateNumbersRequest"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "count"),
    2: .same(proto: "delay"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.count) }()
      case 2: try { try decoder.decodeSingularInt32Field(value: &self.delay) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.count != 0 {
      try visitor.visitSingularInt32Field(value: self.count, fieldNumber: 1)
    }
    if self.delay != 0 {
      try visitor.visitSingularInt32Field(value: self.delay, fieldNumber: 2)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_GenerateNumbersRequest, rhs: Profilog_Playground_GenerateNumbersRequest) -> Bool {
    if lhs.count != rhs.count {return false}
    if lhs.delay != rhs.delay {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_GenerateNumbersResponse: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".GenerateNumbersResponse"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "number"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.number) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.number != 0 {
      try visitor.visitSingularInt32Field(value: self.number, fieldNumber: 1)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_GenerateNumbersResponse, rhs: Profilog_Playground_GenerateNumbersResponse) -> Bool {
    if lhs.number != rhs.number {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_StoreNumbersRequest: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".StoreNumbersRequest"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "number"),
    2: .same(proto: "delay"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.number) }()
      case 2: try { try decoder.decodeSingularInt32Field(value: &self.delay) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.number != 0 {
      try visitor.visitSingularInt32Field(value: self.number, fieldNumber: 1)
    }
    if self.delay != 0 {
      try visitor.visitSingularInt32Field(value: self.delay, fieldNumber: 2)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_StoreNumbersRequest, rhs: Profilog_Playground_StoreNumbersRequest) -> Bool {
    if lhs.number != rhs.number {return false}
    if lhs.delay != rhs.delay {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_StoreNumbersResponse: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".StoreNumbersResponse"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "success"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularBoolField(value: &self.success) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.success != false {
      try visitor.visitSingularBoolField(value: self.success, fieldNumber: 1)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_StoreNumbersResponse, rhs: Profilog_Playground_StoreNumbersResponse) -> Bool {
    if lhs.success != rhs.success {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_StoreAndReturnNumbersRequest: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".StoreAndReturnNumbersRequest"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "number"),
    2: .same(proto: "delay"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.number) }()
      case 2: try { try decoder.decodeSingularInt32Field(value: &self.delay) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.number != 0 {
      try visitor.visitSingularInt32Field(value: self.number, fieldNumber: 1)
    }
    if self.delay != 0 {
      try visitor.visitSingularInt32Field(value: self.delay, fieldNumber: 2)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_StoreAndReturnNumbersRequest, rhs: Profilog_Playground_StoreAndReturnNumbersRequest) -> Bool {
    if lhs.number != rhs.number {return false}
    if lhs.delay != rhs.delay {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_StoreAndReturnNumbersResponse: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".StoreAndReturnNumbersResponse"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "number"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.number) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.number != 0 {
      try visitor.visitSingularInt32Field(value: self.number, fieldNumber: 1)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_StoreAndReturnNumbersResponse, rhs: Profilog_Playground_StoreAndReturnNumbersResponse) -> Bool {
    if lhs.number != rhs.number {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_StoreAndReturnNumbersRequest2: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".StoreAndReturnNumbersRequest2"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "number"),
    2: .same(proto: "delay"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.number) }()
      case 2: try { try decoder.decodeSingularInt32Field(value: &self.delay) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.number != 0 {
      try visitor.visitSingularInt32Field(value: self.number, fieldNumber: 1)
    }
    if self.delay != 0 {
      try visitor.visitSingularInt32Field(value: self.delay, fieldNumber: 2)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_StoreAndReturnNumbersRequest2, rhs: Profilog_Playground_StoreAndReturnNumbersRequest2) -> Bool {
    if lhs.number != rhs.number {return false}
    if lhs.delay != rhs.delay {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_StoreAndReturnNumbersResponse2: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".StoreAndReturnNumbersResponse2"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "sum"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.sum) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.sum != 0 {
      try visitor.visitSingularInt32Field(value: self.sum, fieldNumber: 1)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_StoreAndReturnNumbersResponse2, rhs: Profilog_Playground_StoreAndReturnNumbersResponse2) -> Bool {
    if lhs.sum != rhs.sum {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_StoreAndReturnNumbersRequest3: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".StoreAndReturnNumbersRequest3"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "number1"),
    2: .same(proto: "number2"),
    3: .same(proto: "number3"),
    4: .same(proto: "delay"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.number1) }()
      case 2: try { try decoder.decodeSingularInt32Field(value: &self.number2) }()
      case 3: try { try decoder.decodeSingularInt32Field(value: &self.number3) }()
      case 4: try { try decoder.decodeSingularInt32Field(value: &self.delay) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.number1 != 0 {
      try visitor.visitSingularInt32Field(value: self.number1, fieldNumber: 1)
    }
    if self.number2 != 0 {
      try visitor.visitSingularInt32Field(value: self.number2, fieldNumber: 2)
    }
    if self.number3 != 0 {
      try visitor.visitSingularInt32Field(value: self.number3, fieldNumber: 3)
    }
    if self.delay != 0 {
      try visitor.visitSingularInt32Field(value: self.delay, fieldNumber: 4)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_StoreAndReturnNumbersRequest3, rhs: Profilog_Playground_StoreAndReturnNumbersRequest3) -> Bool {
    if lhs.number1 != rhs.number1 {return false}
    if lhs.number2 != rhs.number2 {return false}
    if lhs.number3 != rhs.number3 {return false}
    if lhs.delay != rhs.delay {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}

extension Profilog_Playground_StoreAndReturnNumbersResponse3: SwiftProtobuf.Message, SwiftProtobuf._MessageImplementationBase, SwiftProtobuf._ProtoNameProviding {
  static let protoMessageName: String = _protobuf_package + ".StoreAndReturnNumbersResponse3"
  static let _protobuf_nameMap: SwiftProtobuf._NameMap = [
    1: .same(proto: "number"),
  ]

  mutating func decodeMessage<D: SwiftProtobuf.Decoder>(decoder: inout D) throws {
    while let fieldNumber = try decoder.nextFieldNumber() {
      // The use of inline closures is to circumvent an issue where the compiler
      // allocates stack space for every case branch when no optimizations are
      // enabled. https://github.com/apple/swift-protobuf/issues/1034
      switch fieldNumber {
      case 1: try { try decoder.decodeSingularInt32Field(value: &self.number) }()
      default: break
      }
    }
  }

  func traverse<V: SwiftProtobuf.Visitor>(visitor: inout V) throws {
    if self.number != 0 {
      try visitor.visitSingularInt32Field(value: self.number, fieldNumber: 1)
    }
    try unknownFields.traverse(visitor: &visitor)
  }

  static func ==(lhs: Profilog_Playground_StoreAndReturnNumbersResponse3, rhs: Profilog_Playground_StoreAndReturnNumbersResponse3) -> Bool {
    if lhs.number != rhs.number {return false}
    if lhs.unknownFields != rhs.unknownFields {return false}
    return true
  }
}
