package main

import "fmt"

/**
实现一个 Reader 类型，它不断生成 ASCII 字符 'A' 的流。
 */
type MyReader struct {
}

func (reader MyReader) Read(b []byte) (int, error) {
	count := 0
	for count = range b {
		b[count] = 'A'
	}
	return count, nil
}
func main() {
	b := make([]byte, 2)
	reader := MyReader{}
	reader.Read(b)
	fmt.Printf("b=%v\n", b)
	fmt.Printf("b=%q\n", b)
}
