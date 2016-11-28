#!/bin/sh
if which mysql > /dev/null ; then
	echo "Mysql is already installed"
	exit 0
fi

ROOT_PASSWORD="12root23@#"

echo "mysql-apt-config mysql-apt-config/repo-codename   select trusty" | /usr/bin/debconf-set-selections
echo "mysql-apt-config mysql-apt-config/select-tools select" | /usr/bin/debconf-set-selections
echo "mysql-apt-config mysql-apt-config/repo-distro select ubuntu" | /usr/bin/debconf-set-selections
echo "mysql-apt-config mysql-apt-config/select-server select mysql-5.7" | /usr/bin/debconf-set-selections
echo "mysql-apt-config mysql-apt-config/select-product select Apply" | /usr/bin/debconf-set-selections

echo "mysql-community-server mysql-community-server/root-pass password $ROOT_PASSWORD" | /usr/bin/debconf-set-selections
echo "mysql-community-server mysql-community-server/re-root-pass password $ROOT_PASSWORD" | /usr/bin/debconf-set-selections
echo "mysql-community-server mysql-community-server/remove-data-dir boolean false" | /usr/bin/debconf-set-selections
echo "mysql-community-server mysql-community-server/data-dir note" | /usr/bin/debconf-set-selections

export DEBIAN_FRONTEND=noninteractive

wget -c -P /tmp http://dev.mysql.com/get/mysql-apt-config_0.6.0-1_all.deb

dpkg --install /tmp/mysql-apt-config_0.6.0-1_all.deb

apt-get update

apt-get --yes --force-yes install mysql-server
exit 