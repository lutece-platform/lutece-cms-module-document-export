<%@ page errorPage="../../../../ErrorPage.jsp" %>

<jsp:useBean id="documentExport" scope="session" class="fr.paris.lutece.plugins.document.modules.export.web.DocumentExportJspBean" />
<jsp:useBean id="document" scope="session" class="fr.paris.lutece.plugins.document.web.DocumentJspBean" />
<% 
	documentExport.init(request, document.RIGHT_DOCUMENT_MANAGEMENT);
		
	response.sendRedirect( documentExport.doExportDocumentsDataList( request, response ) );
%>
