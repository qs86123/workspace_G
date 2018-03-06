package main

import (
	"fmt"
	"net/http"
)

type appHandler func(http.ResponseWriter, *http.Request) *appError

type appError struct {
	Error error
	Msg   string
	Code  int
}

func (fn appHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Println("serveHTTP...")
	if e := fn(w, r); e != nil {
		http.Error(w, e.Msg, e.Code)
	}
}

func viewRecord(w http.ResponseWriter, r *http.Request) *appError {
	fmt.Println("coming...")
	var err error
	return &appError{err, "Can't display record", 500}
}

func main() {
	http.ListenAndServe("localhost:8080", nil)
}

func init() {
	http.Handle("/view", appHandler(viewRecord))
}
