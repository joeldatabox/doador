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

}