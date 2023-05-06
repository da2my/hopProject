(defproject hop-tutorial "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.9.8"
  :dependencies [[org.clojure/clojure "1.11.0"]
                 [duct/core "0.8.0"]
                 [duct/module.web
                  "0.7.3"
                  :exclusions
                  [[ring/ring-core] [ring/ring-devel] [ring/ring-jetty-adapter]]]
                 [ring "1.9.6"]
                 [duct/module.logging "0.5.0"]
                 [duct/logger.timbre "0.5.0"]
                 [metosin/reitit "0.5.18"]
                 [metosin/malli "0.9.2"]
                 [org.clojure/clojurescript "1.11.60"]
                 [cljs-ajax/cljs-ajax "0.8.4"]
                 [cljsjs/react "17.0.2-0"]
                 [cljsjs/react-dom "17.0.2-0"]
                 [day8.re-frame/http-fx "0.2.4"]
                 [re-frame/re-frame "1.1.2"]
                 [reagent/reagent "1.1.1"]
                 [com.taoensso/tempura "1.3.0"]
                 [dev.gethop/duct.module.cljs-compiler "0.1.0"]
                 [duct/compiler.sass "0.2.1"]]
  :plugins [[duct/lein-duct "0.12.3"]]
  :main ^:skip-aot hop_tutorial.main
  :resource-paths ["resources" "target/resources" "target/resources/hop_tutorial"]
  :middleware [lein-duct.plugin/middleware]
  :profiles {:dev [:project/dev :profiles/dev]
             :repl {:prep-tasks ^:replace ["javac" "compile"]
                    :dependencies [[cider/piggieback "0.5.2"]]
                    :repl-options {:init-ns user
                                   :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                                   :host "0.0.0.0"
                                   :port 4001}}
             :uberjar {:aot :all :prep-tasks ["javac" "compile" ["run" ":duct/compiler"]]}
             :profiles/dev {}
             :project/dev {:eastwood {:linters [:all]
                                      :exclude-linters [:keyword-typos
                                                        :boxed-math
                                                        :non-clojure-file
                                                        :performance
                                                        :unused-namespaces]
                                      :debug [:progress :time]}
                           :resource-paths ["dev/resources"]
                           :source-paths ["dev/src"]
                           :plugins [[lein-cljfmt "0.8.0"] [jonase/eastwood "1.2.3"]]
                           :dependencies [[integrant/repl "0.3.2"]
                                          [hawk "0.2.11"]
                                          [eftest "0.5.9"]
                                          [ring/ring-mock "0.4.0"]
                                          [day8.re-frame/re-frame-10x "1.2.7"]]}}
  :uberjar-name "hop-tutorial-standalone.jar"
  :test-selectors {:default (fn [m] (not (or (:integration m) (:regression m))))
                   :all (constantly true)
                   :integration :integration
                   :regression :regression})
