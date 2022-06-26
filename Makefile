# 開発のみ -------------------------------------------

# デバッグ用
test:
	sbt "testOnly sample.SampleTest"


# 開発 & 本番 ----------------------------------------
run:
	sbt "runMain sample.Top"
