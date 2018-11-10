# MoveAssetFileAndroid
Simple Example to move file from assets folder to custom folder specified.

#Limitation
We cannot use Resource folder as it have limitation of 1MB ,Otherwise you will face gradle error.

Whereas in assets folder you  have freedom upto 4GB, that can be achived by changing gradle.properties file.
`org.gradle.jvmargs=-Xmx15036m`

This property size can be increased only upto 4GB.
