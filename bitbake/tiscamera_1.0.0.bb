DESCRIPTION = "The Imaging Source drivers and gstreamer plugins" 
LICENSE = "Apache-2.0"

DEPENDS += "gstreamer1.0 gstreamer1.0-plugins-base libusb libzip"

LIC_FILES_CHKSUM = "file://LICENSE;md5=76f9b634bc5499ed996452ef6f4456dc"

SRC_URI = "git://github.com/TheImagingSource/tiscamera.git;branch=development;protocol=https"
SRCREV = "dd20eed75267a6bf5e80b93fb5da572e29bc35a2"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += " \
    -DTCAM_BUILD_ARAVIS=OFF \
	-DTCAM_ARAVIS_USB_VISION=OFF \
	-DTCAM_BUILD_WITH_GUI=OFF \
	-DTCAM_BUILD_DOCUMENTATION=OFF \
	-DTCAM_BUILD_TESTS=OFF \
	-DINTROSPECTION_SCANNER=${WORKDIR}/recipe-sysroot/usr/bin/g-ir-scanner-wrapper \
	-DINTROSPECTION_COMPILER=${WORKDIR}/recipe-sysroot/usr/bin/g-ir-compiler-wrapper \
	-DINTROSPECTION_GENERATE=${WORKDIR}/recipe-sysroot/usr/bin/g-ir-generate \
"

INSANE_SKIP:${PN} = "dev-so"

PACKAGES =+ "${PN}-examples"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""

FILES:${PN} += "\
	${libdir}/libtcam* \
	${libdir}/gstreamer-1.0/libgsttcamsrc.so \
	${libdir}/gstreamer-1.0/libgsttcambin.so \
	${libdir}/gstreamer-1.0/libtcamconvert.so \
	${datadir}/theimagingsource/tiscamera/uvc-extension \
	${datadir}/theimagingsource/tiscamera/images \
	${datadir}/bash-completion \
"

FILES:${PN}-dev += "\
	${includedir} \
	${libdir}/pkgconfig \
"

FILES:${PN}-examples += "\
	${datadir}/theimagingsource/tiscamera/examples \
"

inherit pkgconfig cmake python3native gobject-introspection
