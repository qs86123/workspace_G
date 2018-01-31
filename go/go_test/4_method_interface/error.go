package main

import (
	"time"
	"fmt"
)

type MyError struct {
	When time.Time
	What string
}

func (e *MyError) Error() string {
	return fmt.Sprintf("at %v ,%s", e.When, e.What)
}

func run() error {
	return &MyError{time.Now(), "there is sth wrong"}
}

func main() {
	if err := run(); err != nil {
		fmt.Println(err)
	}
}
