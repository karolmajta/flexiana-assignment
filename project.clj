(defproject flexiana-assignment "1.0.0"
  :description "Assignment from Flexiana"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.0"]
                 [ring-cors "0.1.13"]
                 [liberator "0.15.3"]
                 [compojure "1.6.1"]]
  :source-paths ["src/main"]
  :test-paths ["src/test"]
  :main flexiana-assignment.core
  :plugins [[lein-cljfmt "0.6.6"]
            [lein-ring "0.12.5"]
            [lein-figwheel "0.5.19"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]
  :ring {:handler flexiana-assignment.core/api-handler}
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.10.0"]
                                  [org.clojure/clojurescript "1.10.520"]
                                  [reagent "0.9.0-rc4"]
                                  [org.clojure/core.async "0.6.532"]
                                  [cljs-http "0.1.46"]]}}
  :clean-targets [:target-path "out"]
  :figwheel {:css-dirs ["resources/public/css"]}
  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel {:open-urls ["http://localhost:3449/index.html"]}
                :compiler {:main flexiana-assignment.app
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/app.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}]})
