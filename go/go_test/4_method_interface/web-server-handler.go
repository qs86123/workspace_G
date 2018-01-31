package main

import (
	"net/http"
	"fmt"
)

type String string

type Struct struct {
	Greeting string
	Punct    string
	Who      string
}

func (h String) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(w, string(h))
}

func (h Struct) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(w, h.Who, h.Punct, h.Greeting)
}

func main() {
	http.Handle("/string", String("this is string"))
	http.Handle("/struct", &Struct{"wt", "am", "I"})
	http.ListenAndServe("localhost:8080", nil)
}
