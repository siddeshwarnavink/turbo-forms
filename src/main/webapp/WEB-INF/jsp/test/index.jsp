<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="turboforms" uri="/WEB-INF/forms.tld" %>

<html>
    <head>
        <title>TurboForm test</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <turboforms:style />
    </head>
    <body class="container">
        <h3>Hello JPS!</h3>
        <form:form modelAttribute="formData" method="POST">
            <turboforms:control name="name" />
            <turboforms:control name="dateTime" />

            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <turboforms:script />
    </body>
</html>
