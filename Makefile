deploy-docs: build copy-docs push-to-github

build:
	@echo 'building ...' && \
	mvn package && \
	mvn site

copy-docs:
	@echo 'copying the docs to ../file.github.io ...' &&\
	rm -rf ../common-commons.github.io/file/* && \
	cp -R ./target/apidocs/* ../common-commons.github.io/file/
	
push-to-github:
	@echo 'pushing the docs to github ...' && \
	cd ../common-commons.github.io && git add --all && git commit -m "deployment on " && git push origin master
