/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.document.modules.export.dto.exportdocument;

import fr.paris.lutece.plugins.document.business.Document;

import java.text.SimpleDateFormat;


/**
 * A document's DTO
 */
public class ExportUserDocumentDTO
{
    public static final String RESOURCE_TYPE = "DOCUMENT_EXPORT";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String EMPTY_STRING = "";
    private int _nIdDocument;
    private String _strTitle;
    private String _strType;
    private String _strState;
    private String _strDateValidityBegin;
    private String _strDateValidityEnd;
    private String _strDateCreation;
    private String _strDateModification;
    private String _strSpacePath;

    /**
     * Contructor from Document
     * @param document the document
     */
    public ExportUserDocumentDTO( Document document )
    {
        this._nIdDocument = document.getId(  );
        this._strTitle = document.getTitle(  );
        this._strType = document.getType(  );
        this._strState = document.getState(  );
        this.setDateValidityBegin( document.getDateValidityBegin(  ) );
        this.setDateValidityEnd( document.getDateValidityEnd(  ) );
        this.setDateCreation( document.getDateCreation(  ) );
        this.setDateModification( document.getDateModification(  ) );
    }

    /**
     * provides the parameters of the class in String array
     * @return String array
     */
    public String[] toTabString(  )
    {
        String[] tabStrTemp = 
            {
                String.valueOf( this.getId(  ) ), this.getTitle(  ), this.getType(  ), this.getState(  ),
                this.getDateValidityBegin(  ), this.getDateValidityEnd(  ), this.getDateCreation(  ),
                this.getDateModification(  ), this.getSpacePath(  ),
            };

        return tabStrTemp;
    }

    /**
     * Returns the IdDocument
     *
     * @return The IdDocument
     */
    public int getId(  )
    {
        return _nIdDocument;
    }

    /**
     * Sets the IdDocument
     *
     * @param nIdDocument The IdDocument
     */
    public void setId( int nIdDocument )
    {
        _nIdDocument = nIdDocument;
    }

    /**
     * Returns the Title
     *
     * @return The Title
     */
    public String getTitle(  )
    {
        return _strTitle;
    }

    /**
     * Sets the Title
     *
     * @param strTitle The Title
     */
    public void setTitle( String strTitle )
    {
        _strTitle = strTitle;
    }

    /**
     * Returns the DateCreation
     *
     * @return The DateCreation
     */
    public String getDateCreation(  )
    {
        return _strDateCreation;
    }

    /**
     * Sets the DateCreation
     *
     * @param dateCreation The DateCreation
     */
    public void setDateCreation( String dateCreation )
    {
        _strDateCreation = dateCreation;
    }

    /**
     * Sets the DateCreation
     *
     * @param dateCreation The DateCreation
     */
    public final void setDateCreation( java.sql.Timestamp dateCreation )
    {
        if ( dateCreation == null )
        {
            _strDateCreation = EMPTY_STRING;
        }
        else
        {
            _strDateCreation = new SimpleDateFormat( DATE_FORMAT ).format( dateCreation );
        }
    }

    /**
     * Returns the Date of the last Modification
     *
     * @return The Date of the last Modification
     */
    public String getDateModification(  )
    {
        return _strDateModification;
    }

    /**
     * Sets the Date of the last Modification
     *
     * @param dateModification The Date of the last Modification
     */
    public void setDateModification( String dateModification )
    {
        _strDateModification = dateModification;
    }

    /**
     * Sets the Date of the last Modification
     *
     * @param dateModification The Date of the last Modification
     */
    public final void setDateModification( java.sql.Timestamp dateModification )
    {
        if ( dateModification == null )
        {
            _strDateModification = EMPTY_STRING;
        }
        else
        {
            _strDateModification = new SimpleDateFormat( DATE_FORMAT ).format( dateModification );
        }
    }

    /**
     * Returns the begining Date of the validity period of the document
     *
     * @return The begining Date of the validity period of the document
     */
    public String getDateValidityBegin(  )
    {
        return _strDateValidityBegin;
    }

    /**
     * Sets the begining Date of the validity period of the document
     *
     * @param dateValidityBegin The begining Date of the validity period of the document
     */
    public void setDateValidityBegin( String dateValidityBegin )
    {
        _strDateValidityBegin = dateValidityBegin;
    }

    /**
     * Sets the begining Date of the validity period of the document
     *
     * @param dateValidityBegin The begining Date of the validity period of the document
     */
    public final void setDateValidityBegin( java.sql.Timestamp dateValidityBegin )
    {
        if ( dateValidityBegin == null )
        {
            _strDateValidityBegin = EMPTY_STRING;
        }
        else
        {
            _strDateValidityBegin = new SimpleDateFormat( DATE_FORMAT ).format( dateValidityBegin );
        }
    }

    /**
     * Returns the end Date of the validity period of the document
     *
     * @return The end Date of the validity period of the document
     */
    public String getDateValidityEnd(  )
    {
        return _strDateValidityEnd;
    }

    /**
     * Sets the end Date of the validity period of the document
     *
     * @param dateValidityEnd The end Date of the validity period of the document
     */
    public void setDateValidityEnd( String dateValidityEnd )
    {
        _strDateValidityEnd = dateValidityEnd;
    }

    /**
     * Sets the end Date of the validity period of the document
     *
     * @param dateValidityEnd The end Date of the validity period of the document
     */
    public final void setDateValidityEnd( java.sql.Timestamp dateValidityEnd )
    {
        if ( dateValidityEnd == null )
        {
            _strDateValidityEnd = EMPTY_STRING;
        }
        else
        {
            _strDateValidityEnd = new SimpleDateFormat( DATE_FORMAT ).format( dateValidityEnd );
        }
    }

    /**
     * Returns the State
     *
     * @return The State
     */
    public String getStateKey(  )
    {
        return _strState;
    }

    /**
     * Returns the State
     *
     * @return The State
     */
    public String getState(  )
    {
        return _strState;
    }

    /**
     * Sets the State
     *
     * @param strState The State
     */
    public void setStateKey( String strState )
    {
        _strState = strState;
    }

    /**
     * Returns the Type
     *
     * @return The Type
     */
    public String getType(  )
    {
        return _strType;
    }

    /**
     * Sets the Type
     *
     * @param strType The Type
     */
    public void setType( String strType )
    {
        _strType = strType;
    }

    /**
     * Returns the Space Path
     *
     * @return The Space Path
     */
    public String getSpacePath(  )
    {
        return _strSpacePath;
    }

    /**
     * Sets the Space Path
     *
     * @param strSpacePath The Space Path
     */
    public void setSpacePath( String strSpacePath )
    {
        _strSpacePath = strSpacePath;
    }
}
