package main

import (
	"net/http"
	"fmt"
	"log"
)

type Hello struct {
}

func (h Hello) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(w, "hello")
}

func main() {
	var h Hello
	err := http.ListenAndServe("localhost:8080", h)
	if err != nil {
		log.Fatal(err)
	}
}
