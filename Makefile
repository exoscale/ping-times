deploy:
	lein clean && lein cljsbuild once min && cd resources/public && aws s3 --profile exoscale --endpoint-url https://sos-ch-dk-2.exo.io sync . s3://ping-times --acl "public-read"
