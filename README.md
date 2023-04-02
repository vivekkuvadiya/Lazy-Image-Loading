# Lazy Image Loading Library
- Used Disk and Memory Cache
- Used OkHttp to Load Image
- Created singleton pattern like glide
- Created ImageRequest Builder to scale new feature

## Video
https://user-images.githubusercontent.com/65214194/229347207-f7936d72-deea-4f2e-a983-8a17feef5395.mp4


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
