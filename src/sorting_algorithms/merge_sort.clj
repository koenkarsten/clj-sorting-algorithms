(ns sorting-algorithms.merge-sort
  "Sorting using merge sort algorithm.")

(defn explode
  "Explodes a vector with one or many values into multiples single value vectors."
  [vals]
  (for [i vals] [i]))

(defn merge-sort
  "Merges and sorts two arrays already sorted."
  [vals1 vals2]
  (let [head1 (first vals1)
        head2 (first vals2)]
    (cond
     (empty? vals1) vals2
     (empty? vals2) vals1
     (< head1 head2) (cons head1 (merge-sort (drop 1 vals1) vals2))
     :else (cons head2 (merge-sort vals1 (drop 1 vals2))))))

(defn process
  [vals]
  (if (empty? (rest vals))
    (first vals)
    (->>  vals
          (partition 2 2 [])
          (map #(merge-sort (first %) (second %)))
          process)))

(defn sort
  "Sorts using the merge sort algorithm.
  Example:
  [1  4  2  3 ]
  [1] [4] [2] [3]
  [1*  4]   [2* 3] --> [1]
  [4*]   [2* 3]    --> [1 2]
  [4*]   [3*]      --> [1 2 3]
  [4*]   []        --> [1 2 3 4]"
  [vals]
  (process (explode vals)))
