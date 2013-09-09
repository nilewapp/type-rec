;; Copyright (c) 2013, Robert welin
(ns type-rec.hello
  (:use [domina :only [by-id value set-value! set-text!]]))

(defn show-cursor-position []
  (let [cursor-position (.-selectionStart (by-id "editor"))]
    (set-text! (by-id "cursor-position") cursor-position)))

(defn init []
  (if (and js/document
           (.-getElementById js/document))
    (let [start-button (.getElementById js/document "start")]
      (js/setInterval show-cursor-position 1000))))

(set! (.-onload js/window) init)
