package main

import (
	"io"
	"os"
	"strings"
)

type rot13Reader struct {
	r io.Reader
}

func (reader rot13Reader) Read(b []byte) (int, error) {
	rb, nil := reader.r.Read(b)
	if nil != io.EOF {
		for i := 0; i < rb; i++ {
			b[i] += 1
		}
	}
	return rb, nil
}

func main() {
	s := strings.NewReader("Lbh penpxrq gur pbqr!")
	r := rot13Reader{s}
	io.Copy(os.Stdout, &r)
}
