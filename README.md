# SerializableCookieJar
A simple implementation of a persistent CookieJar for OkHttp 3 based on SharedPreferences with Serializable Cookie objects

This library is a simple implementation of a  CookieJar for OkHttp 3 with Serializable Cookie objects. Cookies are stored in SharedPreferences. 

This is still in testing, so feel free to try it and open new Issues if you find bugs or have any suggestions. 



### Download 
[![](https://jitpack.io/v/valdio/SerializableCookieJar.svg)](https://jitpack.io/#valdio/SerializableCookieJar)


**GRADLE**

**Step 1.** Open `build.gradle(Module: app)` and add the JitPack repository to your build file

```gradle

repositories {
	//...
	maven { url 'https://jitpack.io' }
}
```


**Step 2.** Add the dependency

```gradle
	dependencies {
	        compile 'com.github.valdio:SerializableCookieJar:1.0.0'
	}
```

### How to use SerializableCookieJar ?

Create an instance of `SerializableCookieJar` passing a reference to the `Context`. Then add the CookieJar when building the OkHttp client.

```java
SerializableCookieJar serializableCookieJar = new SerializableCookieJar(this);
OkHttpClient client = new OkHttpClient.Builder()
        .cookieJar(serializableCookieJar)
        .build();

```
### Extra features 

1. Clear all Cookies

```java
serializableCookieJar.clearCookies();
```
2. Clear Expired Cookies

```java
serializableCookieJar.clearExpiredCookies(); //still work in progress  
```

### License

```

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


```
