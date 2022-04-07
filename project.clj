(defproject scrambles "0.1.0-SNAPSHOT"
  :description "A fun game to check if two words can be scrambled"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.0"]
                 [metosin/malli "0.8.4"]
                 [metosin/reitit-core "0.5.17"]
                 [metosin/reitit-ring "0.5.17"]
                 [metosin/reitit-middleware "0.5.17"]
                 [metosin/reitit-malli "0.5.17"]
                 [metosin/muuntaja "0.6.8"]]
  :plugins [[lein-ring "0.12.6"]]
  :ring {:handler scrambles.http/app}
  :repl-options {:init-ns scrambles.core})
