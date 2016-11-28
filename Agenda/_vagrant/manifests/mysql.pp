node 'vagrant.puc.com' {
    include mysql::server
}

class mysql::server{
	$mysql_password = "12root23@#"
	$data_base = "agenda"
	
	service { "mysql":
		ensure => running,
		enable => true,
		hasstatus => true,
		hasrestart => true,
	}

	exec { "set-mysql-password":
    	command => "mysqladmin -uroot -proot password '$mysql_password'",
    	unless => "/usr/bin/mysql -uroot -p$mysql_password",
    	path => ["/bin", "/usr/bin"],
    	require => Service["mysql"]
  	}

	exec{ "grant-all":
		command => "/usr/bin/mysql -uroot -p$mysql_password -e \"GRANT ALL ON *.* TO 'root'@'%' IDENTIFIED BY '$mysql_password';\"",
		unless => "/bin/grep -o -a -h \"0.0.0.0\" /etc/mysql/my.cnf",
		require => Exec["set-mysql-password"]
	}

  	exec{ "listen_all":
   		command => "/bin/sed -i 's/127.0.0.1/0.0.0.0/g' /etc/mysql/my.cnf; /usr/bin/service mysql restart",
   		unless => "/bin/grep -o -a -h \"0.0.0.0\" /etc/mysql/my.cnf",
   		require => Exec["grant-all"]
	}

	exec{"database":
		command => "/usr/bin/mysql -uroot -p$mysql_password -e \"CREATE DATABASE $data_base\"",
		unless => "/usr/bin/mysql -uroot -p$mysql_password $data_base",
		require => Exec["listen_all"]
	}

}