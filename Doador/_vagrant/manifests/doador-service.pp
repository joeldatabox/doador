node 'vagrant.puc.com' {
    include spring::server
}

class spring::server{
    exec {"mkdir_doador-service":
        command => "/bin/mkdir /opt/doador-service",
        unless => "/usr/bin/test -d /opt/doador-service"
    }

    exec {"copy":
        command => "/bin/cp /vagrant/target/doador-service.jar /opt/doador-service/doador-service.jar",
        unless => "/usr/bin/test -e /opt/doador-service/doador-service.jar",
        require => Exec["mkdir_doador-service"]
    }

}