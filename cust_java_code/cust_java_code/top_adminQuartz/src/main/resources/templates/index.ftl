<#import "/spring.ftl" as s />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>交运配货网-后台管理</title>
</head>
<body>
    <div align="center">
        <a href="${request.contextPath}/test/taskJob.do">日常任务</a><br>
        <a href="${request.contextPath}/test/birthdaySmsTask.do">定时发送生日短信任务</a><br>
        <a href="${request.contextPath}/test/bonusAndScoreSettingJob.do">补贴发放</a><br>
        <a href="${request.contextPath}/test/recommendationJob.do">推荐补贴</a><br>
    </div>

</body>
</html>