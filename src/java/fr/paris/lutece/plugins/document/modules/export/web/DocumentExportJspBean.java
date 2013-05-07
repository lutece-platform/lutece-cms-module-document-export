/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.document.modules.export.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.paris.lutece.plugins.document.business.DocumentFilter;
import fr.paris.lutece.plugins.document.modules.export.dto.exportdocument.ExportUserDocumentDTO;
import fr.paris.lutece.plugins.document.modules.export.service.DocumentExportService;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.portal.service.util.AppPathService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.portal.web.admin.PluginAdminPageJspBean;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * 
 * The DocumentExport JspBean
 * 
 */
public class DocumentExportJspBean extends PluginAdminPageJspBean
{

	// constants
	private static final char CONSTANT_SEPARATOR = ';';

	private static final String CONSTANT_DOCUMENT_PLUGIN_DEFAULT_NAME = "document";

	private static final String CONSTANT_EXPORT_FILE_NAME_DEFAULT = "documents_export.csv";

	private static final String[] CONSTANT_HEADER_CSV_FILE =
	{ "Identifiant", "Titre", "Type", "Etat", "Date de début de validité", "Date de fin de validité", "Date de création", "Date de modification", "Espace" };

	// properties
	private static final String PROPERTY_EXPORT_FILE_NAME = "document-export.export_documents.file.name";

	private static final String PROPERTY_EXPORT_FILE_ENCODING = "document-export.export_documents.file.encoding";

	private static final String PROPERTY_DOCUMENT_PLUGIN_NAME = "document-export.plugin.document.name";

	private static final String PROPERTY_PLUGIN_DISABLED = "module.document.export.error.plugin.disabled.message";

	private DocumentExportService _documentExportService = ( DocumentExportService ) SpringContextService.getPluginBean( "document-export", "document-export.exportService" );

	/**
	 * Effectue l'export de la liste des documents
	 * @param request la requete Http
	 * @param response la reponse
	 * @return
	 */
	public String doExportDocumentsDataList( HttpServletRequest request, HttpServletResponse response )
	{
		if ( !PluginService.isPluginEnable( AppPropertiesService.getProperty( PROPERTY_DOCUMENT_PLUGIN_NAME, CONSTANT_DOCUMENT_PLUGIN_DEFAULT_NAME ) ) )
		{

			return AdminMessageService.getMessageUrl( request, PROPERTY_PLUGIN_DISABLED, AdminMessage.TYPE_ERROR );
		}

		DocumentFilter filter = new DocumentFilter();
		/*
		 * Construction du filtre
		 */

		Locale locale = getLocale();

		List<ExportUserDocumentDTO> listExportResults = _documentExportService.getListDocumentByFilter( filter, locale );

		try
		{
			StringWriter strWriter = new StringWriter();
			CSVWriter csvWriter = new CSVWriter( strWriter, CONSTANT_SEPARATOR );
			csvWriter.writeNext( CONSTANT_HEADER_CSV_FILE );
			for ( ExportUserDocumentDTO documentData : listExportResults )
			{
				csvWriter.writeNext( documentData.toTabString() );
			}
			String strEncoding = AppPropertiesService.getProperty( PROPERTY_EXPORT_FILE_ENCODING );
			byte[] byteFileOutPut = strWriter.toString().getBytes( strEncoding );

			String strFileName = AppPropertiesService.getProperty( PROPERTY_EXPORT_FILE_NAME, CONSTANT_EXPORT_FILE_NAME_DEFAULT );
			response.setHeader( "Content-Disposition", "attachment; filename=\"" + strFileName + " \";" );
			response.setHeader( "Pragma", "public" );
			response.setHeader( "Expires", "0" );
			response.setHeader( "Cache-Control", "must-revalidate,post-check=0,pre-check=0" );
			response.setContentType( "enctype=multipart/form-data" );

			OutputStream os = response.getOutputStream();
			os.write( byteFileOutPut );
			os.close();

			csvWriter.close();
			strWriter.close();

		}
		catch ( IOException e )
		{
			AppLogService.error( e.getMessage(), e );
		}
		catch ( Exception e )
		{
			AppLogService.error( e.getMessage(), e );
		}

		return AppPathService.getBaseUrl( request );

	}

}
