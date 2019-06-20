(ns juxt.jsonschema.regex-cljc-test
  (:require
   [juxt.jsonschema.regex-cljc :as regex]
   [cljs.test :refer-macros [deftest is are testing run-tests]]))

;TODO grouped regex not supported in javascript
; (deftest iri-test
;   (let [m (regex/matched regex/IRI "https://jon:password@juxt.pro:8080/site/index.html?debug=true#bar")]
;     (are [group expected] (= expected (regex/re-group-by-name m group))
;       "scheme" "https"
;       "authority" "jon:password@juxt.pro:8080"
;       "userinfo" "jon:password"
;       "host" "juxt.pro"
;       "port" "8080"
;       "path" "/site/index.html"
;       "query" "debug=true"
;       "fragment" "bar")))

(deftest addr-spec-test
  (are [x y] (= y (regex/parse "addr-spec-test" x))
    "mal@juxt.pro"
    ["mal" "juxt.pro"]))

(deftest iri-test
  (are [x y] (= y (regex/parse "iri-test" x))
    "https://jon:password@juxt.pro:8080/site/index.html?debug=true#bar"
    ["https" "jon:password"  "juxt.pro" "8080" "/site/index.html" "debug=true"  "bar" ]
    
    "/happy/path"
    [nil nil nil nil "/happy/path" nil nil]

    "relative/path"
    [nil nil nil nil "relative/path" nil nil]

    "http://user:password@example.com:8080/path?query=value#fragment"
    ["http" "user:password" "example.com" "8080" "/path" "query=value" "fragment"]
    
    ; "http://example.com"
    ; ["http" nil nil "example.com" nil nil nil nil]
    
    ))
;; TODO: ipv6 tests from rfc2234
