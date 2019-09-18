require chromium-gn.inc

SRC_URI += " \
        file://0001-ozone-wayland-Fix-method-prototype-match.patch \
        file://V4L2/0001-Add-support-for-V4L2VDA-on-Linux.patch \
        file://V4L2/0002-Add-mmap-via-libv4l-to-generic_v4l2_device.patch \
        file://0002-wayland-disable-gbm.patch \
        file://0003-wayland-disable-dri.patch \
        file://0004-wayland-disable--is-desktop-linux.patch \
        file://0005-ptrace-glibc-2.24.patch \
        file://0006-do-not-link-atomic.patch \
        file://0007-disable-mojom.patch \
        file://0008-remove-plugin-finder.patch \
        file://0009-enable-generating-ids-webauth.patch \
        file://0010-disable-v8-use-snapshot.patch \
        file://0011-linker-wayland-non-gbm.patch \
        file://0012-linker-createhatbubble.patch \
        file://0013-linker-browser-switcher-prefs.patch \
        file://0014-linker-shortcut-manager.patch \
        file://0015-linker-tts-platform-impl.patch \
"

REQUIRED_DISTRO_FEATURES = "wayland"

DEPENDS += "\
        libxkbcommon \
        virtual/egl \
        wayland-egl \
"

# Chromium can use v4l2 device for hardware accelerated video decoding. Make sure that
# /dev/video-dec exists.
PACKAGECONFIG[use-linux-v4l2] = "use_v4l2_codec=true use_v4lplugin=true use_linux_v4l2_only=true"

GN_ARGS += "\
        ${PACKAGECONFIG_CONFARGS} \
        use_ozone=true \
        ozone_auto_platforms=false \
        ozone_platform_headless=true \
        ozone_platform_wayland=true \
        ozone_platform_x11=false \
        system_wayland_scanner_path="${STAGING_BINDIR_NATIVE}/wayland-scanner" \
        use_xkbcommon=true \
        use_system_libwayland=true \
        use_system_minigbm=false \
        use_system_libdrm=false \
"

# The chromium binary must always be started with those arguments.
CHROMIUM_EXTRA_ARGS_append = " --ozone-platform=wayland"
