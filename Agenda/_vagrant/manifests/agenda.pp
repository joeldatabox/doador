node 'vagrant.puc.com' {
    include spring::server
}

class spring::server{
    exec {"mkdir_agenda-service":
        command => "/bin/mkdir /opt/agenda-service",
        unless => "/usr/bin/test -d /opt/agenda-service"
    }

    exec {"copy":
        command => "/bin/cp /vagrant/target/agenda-service.jar /opt/agenda-service/agenda-service.jar",
        unless => "/usr/bin/test -e /opt/agenda-service/agenda-service.jar",
        require => Exec["mkdir_agenda-service"]
    }

    exec {"mkdir_agenda-service-init":
        command => "/bin/mkdir /etc/init.d/agenda-service",
        unless => "/usr/bin/test -d /etc/init.d/agenda-service",
        require => Exec["mkdir_agenda-service"]
    }
    exec {"create_scriptInit":
        command => "/bin/cp /vagrant/_vagrant/manifests/agenda-service-init.sh /etc/init.d/agenda-service",
        require => Exec["mkdir_agenda-service-init"],
        unless => ["/usr/bin/test -e /etc/init.d/agenda-service/agenda-sercice-init.sh"]
    }

    exec {"secure_boot":
        command => "/usr/sbin/update-rc.d agenda-service/agenda-service-init.sh ",
        require => Exec["create_scriptInit"]
    }

    service { "agenda-service":
        ensure => running,
        enable => true,
        hasstatus => true,
        hasrestart => true,
        require => Exec["secure_boot"]
    }
}