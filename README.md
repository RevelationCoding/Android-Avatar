[![](https://jitpack.io/v/RevelationCoding/Avatar-Generator-Android.svg)](https://jitpack.io/#RevelationCoding/Avatar-Generator-Android)

# Avatar-Generator-Android

A Material like circular avatar library for android.
Generate first letter avatar Image like Google Contacts and random background.

[Demo Apk](https://github.com/RevelationCoding/Avatar-Generator-Android/blob/master/app/app-debug.apk?raw=true)

## Preview

<img src="https://github.com/RevelationCoding/Android-Avatar/blob/master/art/android_avatar.gif?raw=true" width="207px" height="500px"/>

## Gradle Dependency

-Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

-Add the dependency

```gradle
dependencies {
	        implementation 'com.github.RevelationCoding:Android-Avatar:Tag'
	}
```

## Usage

-Xml

```xml

<!--when random color is set true backgroundColor will be not set-->
<com.RevelationCoding.mylibrary.AndroidAvatar
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_centerHorizontal="true"
           android:layout_centerVertical="true"
           app:text="Name"
           app:backgroundWidth="400"
           app:backgroundHeight="400"
           app:textSize="56"
           app:imageSrc="@drawable/jordan"
           app:randomColor="true"
           app:backgroundColor="@color/dark"
           app:textColor="@color/white" />
```

-Java

```java
AndroidAvatarLib androidAvatarLib;
androidAvatarLib = findViewById(R.id.avatarBack_random);

//to set random color on click
androidAvatarLib.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               androidAvatarLib.setRandomColor();
           }
       });
```

-AndroidAvatarLib Available Methods

```available methods
setChar(char c);
setTextSize(int size);
setRandomColor();
setBackColor(int color);
setTextColor(int color);
setBackgroundHeight(int height);
setBackgroundWidth(int width);
setText(String string);
setImageSrc(Drawable imageFile, int imageError, ImageView.ScaleType scaleType);
setImageUrl(String imageUrl, int imageError, ImageView.ScaleType scaleType);
```

## Contribution

Feel free to submit issues and enhancement requests.
I'm very new to coding and git so I'm absolutely open to feedback and sugggestion. :)
