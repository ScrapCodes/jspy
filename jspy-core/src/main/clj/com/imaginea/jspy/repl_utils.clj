(ns ^{:doc "Repl utilities"}
  com.imaginea.jspy.repl-utils
  (use [com.imaginea.jspy.enhance-class]
       [com.imaginea.jspy.local-profiler]
       [com.imaginea.jspy.vm-command]))

(defn write-file [bytes fileName ]
   (with-open [w (java.io.BufferedOutputStream. (java.io.FileOutputStream. fileName))]
     (.write w bytes)))


(defn print-exception 
  "Prints the exception stack trace"
  []
  (loop [x @allThreadCommonLog]
    (let [y (first x)]
      (if-not (nil? y)
        (do 
          (if (.startsWith y "\t")
            (println y))
          (recur (rest x)))))))

(defn clean-str[command] (clojure.string/replace command "\n" " "))


(defn toInt[x] (Integer/parseInt x 16))

(defn toHex[x] (Integer/toHexString x))


(comment
  (write-file 
   (:bytes 
    (instrument-class 
     (clojure.java.io/input-stream 
      (str (System/getProperty "user.dir") 
           "/profiler/agent/target/test-classes/org/apurba/profiler/TestJournal.class")))) 
   "/tmp/xyz.class"))
