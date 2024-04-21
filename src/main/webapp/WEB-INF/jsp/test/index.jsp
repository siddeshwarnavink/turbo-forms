<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="turboforms" uri="forms.tld" %>

<html>
    <head>
        <title>TurboForm test</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <turboforms:style />
    </head>
    <body class="container">
        <h3>Hello JPS!</h3>
        <form:form modelAttribute="formData" method="POST" enctype="multipart/form-data">
            <turboforms:control name="name" hint="Please use real name" />
            <turboforms:control name="dob" minDate="today" />
            <turboforms:control name="file" accept="image/png, image/jpg"  hint="Please use real face" />
            <turboforms:control name="bio" />

            <button class="btn btn-primary" type="submit">Submit</button>
        </form:form>

        <turboforms:script />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
