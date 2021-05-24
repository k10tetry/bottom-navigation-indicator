[![](https://jitpack.io/v/k10tetry/bottom-navigation-indicator.svg)](https://jitpack.io/#k10tetry/bottom-navigation-indicator)

# Bottom Navigation Indicator

BottomNavigation Indicator is a simple andoird library for adding a smooth indicator animation above bottom navigation view.

## Screenshot

<img src="https://raw.githubusercontent.com/k10tetry/bottom-navigation-indicator/master/screenshot/screenshot_2021.png" width="300"/>

## Installation

Add Jitpack to your project build.gralde file.

```javascript
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Then add this dependency to your app build.gradle file.

```javascript
dependencies {
  ...
  implementation 'com.github.k10tetry:bottom-navigation-indicator:1.0.0'
}
```

## Usage

```javascript
<com.codewithk10.bottomnavigationindicator.BottomNavigationIndicator
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:barColor="@color/colorBlack"
        app:barCorners="round"
        app:barHeight="4dp"
        app:barMode="fixed"
        app:barSweepCount="5"
        app:enableAnimation="true" />
```

## License

MIT License

Copyright (c) 2021 Ketan Tetry

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
