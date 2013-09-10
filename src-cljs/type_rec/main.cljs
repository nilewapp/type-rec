; Copyright (c) 2013, Robert welin
(ns type-rec.main
  (:require [domina :refer [by-id value set-text! append! text]]
            [domina.events :refer [listen! prevent-default]]))

(def tick 1000)

(def key-events [])

(defn show-data []
  (let [cursor-position (.-selectionStart (by-id "editor"))]
    (do (set-text! (by-id "cursor-position") cursor-position)
        (set-text! (by-id "current-input") key-events))))

(defn record-key-event [evt]
  (set! key-events (conj key-events (:charCode evt))))

(defn init []
  (if (and js/document
           (.-getElementById js/document))
    (let [start-button (.getElementById js/document "start")]
      (do (js/setInterval show-data tick)
          (listen! (by-id "editor") :keypress (fn [evt] (record-key-event evt)))))))

(set! (.-onload js/window) init)
