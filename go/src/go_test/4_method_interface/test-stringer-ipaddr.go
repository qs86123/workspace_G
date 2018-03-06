package main

import "fmt"

/**
让 IPAddr 类型实现 fmt.Stringer 以便用点分格式输出地址。

例如，IPAddr{1, 2, 3, 4} 应当输出 "1.2.3.4"。
 */
type IPAddr [4]byte

func (ip IPAddr) String() string {
	l := len(ip)
	if l <= 0 {
		return "{}"
	}
	s := "{"
	for i := 0; i < l-1; i++ {
		s += fmt.Sprintf("%v.", ip[i])
	}
	s += fmt.Sprintf("%v}", ip[l-1])
	return s
}

func main() {
	ip := IPAddr{1, 2, 3, 4}
	ip2 := IPAddr{1, 2}
	fmt.Println(ip, ip2)
}
