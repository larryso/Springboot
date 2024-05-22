# Base64

Base64 is a group of similar binary-to-text encoding schemes that represent binary data in an ASCII string format by translating it into a radix-64 representation. The term Base64 originates from a specific MIME content transfer encoding.

When the term "Base64" is used on its own to refer to a specific algorithm, it typically refers to the version of Base64 outlined in RFC 4648, section 4, which uses the following alphabet to represent the radix-64 digits, alongside = as a padding character:

`ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/`

为什么要使用 base64?

我们知道一个字节可表示的范围是 0 ～ 255（十六进制：0x00 ～ 0xFF）， 其中 ASCII 值的范围为 0 ～ 127（十六进制：0x00 ～ 0x7F）；而超过 ASCII 范围的 128～255（十六进制：0x80 ～ 0xFF）之间的值是不可见字符。
当不可见字符在网络上传输时，比如说从 A 计算机传到 B 计算机，往往要经过多个路由设备，由于不同的设备对字符的处理方式有一些不同，这样那些不可见字符就有可能被处理错误，这是不利于传输的。

为了解决这个问题，我们可以先对数据进行编码，比如 base64 编码，变成可见字符，也就是 ASCII 码可表示的可见字符，从而确保数据可靠传输。Base64 的内容是有 0 ～ 9，a ～ z，A ～ Z，+，/ 组成，正好 64 个字符，这些字符是在 ASCII 可表示的范围内，属于 95 个可见字符的一部分。

A common variant is "Base64 URL safe", which omits the padding and replaces +/ with -_ to avoid characters that might cause problems in URL path segments or query parameters.

Base64 encoding schemes are commonly used to encode binary data for storage or transfer over media that can only deal with ASCII text (or some superset of ASCII that still falls short of accepting arbitrary binary data). This ensures that the data remains intact without modification during transport. Common applications of Base64 include:

base64 编码的应用
1. 实现简单的数据加密，使用户一眼望去完全看不出真实数据内容，base64算法的复杂程度要小，效率要高相对较高。 
2. Base64编码的主要的作用不在于安全性，而在于让内容能在各个网关间无错的传输，这才是Base64编码的核心作用。 
3. 在计算机中任何数据都是按ascii码存储的，而ascii码的128～255之间的值是不可见字符。而在网络上交换数据时，比如说从A地传到B地，往往要经过多个路由设备，由于不同的设备对字符的处理方式有一些不同，这样那些不可见字符就有可能被处理错误，这是不利于传输的。所以就先把数据先做一个Base64编码，统统变成可见字符，这样出错的可能性就大降低了。
4. Base64 编码在URL中的应用：
   Base64编码可用于在HTTP环境下传递较长的标识信息。例如，在Java持久化系统Hibernate中，就采用了Base64来将一个较长的唯一标识符（一般为128-bit的UUID）编码为一个字符串，用作HTTP表单和HTTP GET URL中的参数。在其他应用程序中，也常常需要把二进制数据编码为适合放在URL（包括隐藏表单域）中的形式。此时，采用Base64编码不仅比较简短，同时也具有不可读性，即所编码的数据不会被人用肉眼所直接看到。

   然而，标准的Base64并不适合直接放在URL里传输，因为URL编码器会把标准Base64中的“/”和“+”字符变为形如“%XX”的形式，而这些“%”号在存入数据库时还需要再进行转换，因为ANSI SQL中已将“%”号用作通配符。

（1）为解决此问题，可采用一种用于URL的改进Base64编码，它不在末尾填充'='号，并将标准Base64中的“+”和“/”分别改成了“-”和“”，这样就免去了在URL编解码和数据库存储时所要作的转换，避免了编码信息长度在此过程中的增加，并统一了数据库、表单等处对象标识符的格式。 （2）另有一种用于正则表达式的改进Base64变种，它将“+”和“/”改成了“!”和“-”，因为“+”，“”以及前面在IRCu中用到的“[”和“]”在正则表达式中都可能具有特殊含义。 此外还有一些变种，它们将“+/”改为“-”或“.”（用作编程语言中的标识符名称）或“.-”（用于XML中的Nmtoken）甚至“*:”（用于XML中的Name）。

Encoded size increase

Each Base64 digit represents 6 bits of data. So, three 8-bit bytes of the input string/binary file (3×8 bits = 24 bits) can be represented by four 6-bit Base64 digits (4×6 = 24 bits).

This means that the Base64 version of a string or file is typically roughly a third larger than its source (the exact size increase depends on various factors, such as the absolute length of the string, its length modulo 3, and whether padding characters are used).

[https://blog.csdn.net/6346289/article/details/115743752](https://blog.csdn.net/6346289/article/details/115743752)

[https://www.jianshu.com/p/b611e220ef2d](https://www.jianshu.com/p/b611e220ef2d)

## Java Base64 Encoding and Decoding

[https://www.baeldung.com/java-base64-encode-and-decode](https://www.baeldung.com/java-base64-encode-and-decode)

