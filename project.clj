(defproject type-rec "0.1.0-SNAPSHOT"
  :description "Records and plays out the action of typing."
  :source-paths ["src-clj"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.0.4"]
                 [hiccup "1.0.0"]
                 [domina "1.0.0"]]
  :plugins [[lein-cljsbuild "0.3.2"]
            [lein-ring "0.7.0"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {
    :builds [{:source-paths ["src-cljs"]
              :compiler {:output-to "resources/public/js/main.js"
                         :optimizations :whitespace
                         :pretty-print true}}]}
  :ring {:handler type-rec.routes/app})
