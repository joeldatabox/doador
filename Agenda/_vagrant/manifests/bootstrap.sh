#!/bin/bash	

if which puppet > /dev/null ; then
	echo "Puppet is already installed"
	exit 0
fi

export DEBIAN_FRONTEND=noninteractive
wget -qO /tmp/puppetlabs-release-trusty.deb https://apt.puppetlabs.com/puppetlabs-release-trusty.deb

dpkg -i /tmp/puppetlabs-release-trusty.deb
rm /tmp/puppetlabs-release-trusty.deb
aptitude update
echo Installing puppet
aptitude install -y puppet
echo "Puppet installed!"