# Lazy Image Loading Library
- Used Disk and Memory Cache
- Used OkHttp to Load Image
- Created singleton pattern like glide
- Created ImageRequest Builder to scale new feature


## To get a Git project into your build:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
	dependencies {
	        implementation 'com.github.vivekkuvadiya:Lazy-Image-Loading:1.0.0'
	}
```
