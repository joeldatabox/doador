node 'vagrant.puc.com' {
    include spring::server
}

class spring::server{
    exec {"build":
        command => "/usr/bin/mvn package -Dmaven.test.skip=true",
        unless => "/usr/bin/test -e /vagrant/target/agenda-0.0.1-SNAPSHOT.jar"
    }

    exec {"mkdir_agenda-service"
        command => "/bin/mkdir /opt/agenda-service"
        unless => "/usr/bin/test -d /opt/agenda-service"
        require => Exec["build"]
    }

    exec {"copy":
        command => "/usr/bin/mvn package -Dmaven.test.skip=true",
        unless => "/usr/bin/test -e /vagrant/target/agenda-0.0.1-SNAPSHOT.jar"
        require => Exec["mkdir_agenda-service"]
    }
}