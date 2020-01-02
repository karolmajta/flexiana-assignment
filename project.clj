(defproject flexiana-assignment "1.0.0"
  :description "Assignment from Flexiana"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.0"]
                 [liberator "0.15.3"]
                 [compojure "1.6.1"]]
  :source-paths ["src/main"]
  :test-paths ["src/test"]
  :main flexiana-assignment.core
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler flexiana-assignment.core/api-handler}
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.10.0"]]}})
