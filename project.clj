(defproject flexiana-assignment "1.0.0"
  :description "Assignment from Flexiana"
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :source-paths ["src/main"]
  :test-paths ["src/test"]
  :main flexiana-assignment.core
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.10.0"]]}})
