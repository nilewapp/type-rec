; Copyright (c) 2013, Robert welin
(ns type-rec.main
  (:require [domina :refer [by-id value set-text! set-value! append! text]]
            [domina.events :refer [listen! unlisten! dispatch! prevent-default]]))

(def tick 100)

(def key-events [])

(def key-tick-buffer [])

(defn dispatch-key [k]
  (dispatch! (by-id "editor") :keypress {:charCode k}))

(defn show-data []
  (let [cursor-position (.-selectionStart (by-id "editor"))]
    (do (set! key-events (conj key-events key-tick-buffer))
        (set-text! (by-id "cursor-position") cursor-position)
        (set-text! (by-id "current-input") key-tick-buffer)
        ;(set-value! (by-id "editor") (apply str (map String/fromCharCode (flatten key-events))))
        (set! key-tick-buffer []))))

(defn record-key-event [evt]
  (set! key-tick-buffer (conj key-tick-buffer (:charCode evt))))

(defn init []
  (if (and js/document
           (.-getElementById js/document))
    (let [start-button (.getElementById js/document "start")]
      (do (js/setInterval show-data tick)
          (listen! (by-id "editor") :keypress (fn [evt] (record-key-event evt)))))))

(set! (.-onload js/window) init)
