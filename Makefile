deploy-docs: build copy-docs push-to-github

build:
	@echo 'building ...' && \
	mvn package && \
	mvn site

copy-docs:
	@echo 'copying the docs to ../common-commons.github.io ...' &&\
	rm -rf ../common-commons.github.io/* && \
	cp -R ./target/apidocs/* ../commom-commons.github.io
	
push-to-github:
	@echo 'pushing the docs to github ...' && \
	cd ../commom-commons.github.io && git add --all && git commit -m "deployment on " && git push origin master
