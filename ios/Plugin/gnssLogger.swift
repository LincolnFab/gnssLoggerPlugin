import Foundation

@objc public class gnssLogger: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
