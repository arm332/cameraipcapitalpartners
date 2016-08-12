# LETSENCRYPT.md

Setup Let's Encrypt certificates on Google App Engine.

### Install the Let's Encrypt client (https://letsencrypt.org/docs/client-options/)

	sudo git clone https://github.com/letsencrypt/letsencrypt /opt/letsencrypt
	
### Test (optional)
	
	# TODO Use --dry-run
	
### Get the certificate

	# Don't press ENTER until the application is setup 
	sudo letsencrypt -a manual certonly --agree-tos --domain cameras.DOMAIN1.com --domain cameras.DOMAIN1.com --domain cameras.DOMAIN2.com --email MAIL@DOMAIN.com --manual-public-ip-logging-ok

	# Make sure your web server displays the following content at
	# http://cameras.domain1.com/.well-known/acme-challenge/QhiYUjasFnd1e5-ThYJD_dAXAeEDi7jtcQwrgFkq_ak before continuing:
	#
	# QhiYUjasFnd1e5-ThYJD_dAXAeEDi7jtcQwrgFkq_ak.csW4RJ3GI3ybuFm1WA4vL61_U1pi6p_9g8Sh07jQ5RU
	#
	# If you don't have HTTP server configured, you can run the following
	# command on the target server (as root):
	# 
	# (...)
	#
	# Press ENTER to continue

### Setup the application

- Open the application
- Open ACME administration
- Add/Edit the domain
- Domínio: cameras.domain1.com
- Token: QhiYUjasFnd1e5-ThYJD_dAXAeEDi7jtcQwrgFkq_ak
- Autenticação: QhiYUjasFnd1e5-ThYJD_dAXAeEDi7jtcQwrgFkq_ak.csW4RJ3GI3ybuFm1WA4vL61_U1pi6p_9g8Sh07jQ5RU
- Go back to Let's Encrypt client
- Press ENTER

### Repeate the processes for all domains

	# IMPORTANT NOTES:
	# - Congratulations! Your certificate and chain have been saved at
	#   /etc/letsencrypt/live/cameras.domain.com/fullchain.pem. Your cert
	#   will expire on 2016-09-18. To obtain a new version of the
	#   certificate in the future, simply run Let's Encrypt again.
	# - If you like Let's Encrypt, please consider supporting our work by:
	#
	#   Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
	#   Donating to EFF:                    https://eff.org/donate-le

### Copy the public key

	sudo cat /etc/letsencrypt/live/cameras.DOMAIN1.com/fullchain.pem >~/fullchain.pem

### Convert the private key to RSA

	sudo openssl rsa -in /etc/letsencrypt/live/cameras.DOMAIN1.com/privkey.pem > ~/privkey-rsa.pem
	
### Download

	scp USER@DOMAIN.com:~/fullchain.pem .
	scp USER@DOMAIN.com:~/privkey-rsa.pem .
	
### Upload to Google App Engine

- Goole Console
- Google App Engine
- Configurações
- Adicionar um novo certificado SSL
- Certificado de chave pública X.509 codificado em PEM: fullchain.pem
- Chave privada RSA não criptografada codificada em PEM: privkey-rsa.pem

