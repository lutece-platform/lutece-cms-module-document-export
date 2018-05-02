/*
 * Copyright (c) 2002-2017, Mairie de Paris
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
package fr.paris.lutece.plugins.document.modules.export.service;

import fr.paris.lutece.plugins.document.business.Document;
import fr.paris.lutece.plugins.document.business.DocumentFilter;
import fr.paris.lutece.plugins.document.business.DocumentHome;
import fr.paris.lutece.plugins.document.modules.export.business.DocumentExportHome;
import fr.paris.lutece.plugins.document.modules.export.dto.exportdocument.ExportUserDocumentDTO;
import fr.paris.lutece.plugins.document.modules.export.dto.space.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * service for document export
 */
public class DocumentExportService
{
    private static final char CONSTANT_SPACE_SEPARATOR = '/';

    /**
     * search documents from filter
     * @param filter the filter
     * @param locale the locale
     * @return list of ExportUserDocumentDTO
     */
    public List<ExportUserDocumentDTO> getListDocumentByFilter( DocumentFilter filter, Locale locale )
    {
        List<ExportUserDocumentDTO> listExportUserDocument = new ArrayList<ExportUserDocumentDTO>(  );
        List<Document> listDocument = DocumentHome.findByFilter( filter, locale );

        for ( Document document : listDocument )
        {
            ExportUserDocumentDTO exportUserDocument = new ExportUserDocumentDTO( document );
            exportUserDocument.setSpacePath( getSpacePath( document.getSpaceId(  ), document.getSpace(  ) ) );
            listExportUserDocument.add( exportUserDocument );
        }

        return listExportUserDocument;
    }

    /**
     * build space path
     * @param nIdSpace the id space
     * @param strSpaceName the space name
     * @return SpacePath
     */
    public String getSpacePath( int nIdSpace, String strSpaceName )
    {
        String strSpacePath = strSpaceName;
        int nIdSpaceParent = -1;
        int nId = nIdSpace;

        while ( ( nIdSpaceParent != 0 ) && ( nId != 0 ) )
        {
            Space space = DocumentExportHome.getSpaceParent( nId );
            nId = space.getIdSpace(  );
            nIdSpaceParent = space.getIdParent(  );

            if ( nIdSpaceParent != -1 )
            {
                strSpacePath = space.getSpaceName(  ) + CONSTANT_SPACE_SEPARATOR + strSpacePath;
            }
        }

        return strSpacePath;
    }
}
