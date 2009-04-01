/**
 * Copyright (c) 2008-2009, University of Bristol
 * Copyright (c) 2008-2009, University of Manchester
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1) Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 * 2) Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3) Neither the names of the University of Bristol and the
 *    University of Manchester nor the names of their
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package net.crew_vre.jena.vocabulary;

import com.hp.hpl.jena.rdf.model.*;

/**
 * Vocabulary definitions from http://www.w3.org/2004/02/skos/core/history/2006-04-18.rdf
 * @author Auto-generated by schemagen on 15 Jun 2007 14:56
 */
public class SKOS {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();

    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://www.w3.org/2004/02/skos/core#";

    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS
     *  @return the namespace of the vocabuary is a string
     */
    public static String getURI() {return NS;}

    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );

    public static final Property isPrimarySubjectOf = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#isPrimarySubjectOf" );

    public static final Property editorialNote = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#editorialNote" );

    /** <p>No two concepts in the same concept scheme may have the same value for skos:prefLabel
     *  in a given language.</p>
     */
    public static final Property prefLabel = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#prefLabel" );

    /** <p>This property is roughly analagous to rdfs:label, but for labelling resources
     *  with images that have retrievable representations, rather than RDF literals.</p>
     */
    public static final Property symbol = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#symbol" );

    public static final Property hasTopConcept = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#hasTopConcept" );

    public static final Property changeNote = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#changeNote" );

    /** <p>This property should not be used directly, but as a super-property for all
     *  properties denoting a relationship of meaning between concepts.</p>
     */
    public static final Property semanticRelation = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#semanticRelation" );

    public static final Property example = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#example" );

    /** <p>The following rule applies for this property: [(?c skos:memberList ?l) elementOfList(?e,?l)
     *  implies (?c skos:member ?e)]</p>
     */
    public static final Property memberList = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#memberList" );

    public static final Property member = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#member" );

    public static final Property related = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#related" );

    /** <p>A resource may have only one primary subject per concept scheme.</p> */
    public static final Property primarySubject = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#primarySubject" );

    /** <p>No two concepts in the same concept scheme may have the same value for skos:prefSymbol.</p> */
    public static final Property prefSymbol = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#prefSymbol" );

    public static final Property hiddenLabel = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#hiddenLabel" );

    public static final Property historyNote = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#historyNote" );

    /** <p>A concept may be a member of more than one concept scheme.</p> */
    public static final Property inScheme = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#inScheme" );

    /** <p>The following rule may be applied for this property: [(?d skos:subject ?x)(?x
     *  skos:broader ?y) implies (?d skos:subject ?y)]</p>
     */
    public static final Property subject = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#subject" );

    /** <p>This property allows subject indicators to be used for concept identification
     *  in place of or in addition to directly assigned URIs.</p>
     */
    public static final Property subjectIndicator = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#subjectIndicator" );

    public static final Property definition = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#definition" );

    /** <p>Broader concepts are typically rendered as parents in a concept hierarchy
     *  (tree).</p>
     */
    public static final Property broader = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#broader" );

    public static final Property altSymbol = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#altSymbol" );

    public static final Property isSubjectOf = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#isSubjectOf" );

    /** <p>This property may be used directly, or as a super-property for more specific
     *  note types.</p>
     */
    public static final Property note = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#note" );

    /** <p>Narrower concepts are typically rendered as children in a concept hierarchy
     *  (tree).</p>
     */
    public static final Property narrower = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#narrower" );

    public static final Property scopeNote = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#scopeNote" );

    /** <p>Acronyms, abbreviations, spelling variants, and irregular plural/singular
     *  forms may be included among the alternative labels for a concept. Mis-spelled
     *  terms are normally included as hidden labels (see skos:hiddenLabel).</p>
     */
    public static final Property altLabel = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#altLabel" );

    /** <p>An extension of the 'broader' property to specify the class subsumption (sub-class/super-class)
     *  relationship between two concepts. This property is semantically equivalent
     *  to the 'rdfs:subClassOf' property.</p>
     */
    public static final Property broaderGeneric = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#broaderGeneric" );

    /** <p>This property is the inverse of the 'broaderInstantive' property.</p> */
    public static final Property narrowerInstantive = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#narrowerInstantive" );

    /** <p>This property is the inverse of the 'broaderPartitive' property.</p> */
    public static final Property narrowerPartitive = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#narrowerPartitive" );

    /** <p>An extension of the 'related' property. Use this property to express a partitive
     *  relationship between concepts, where it is desired that such a relationship
     *  be treated as associative (i.e. linking separate branches of a hierarchy)
     *  and NOT hierarchical.</p>
     */
    public static final Property relatedPartOf = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#relatedPartOf" );

    /** <p>This property is the inverse of the 'broaderGeneric' property.</p> */
    public static final Property narrowerGeneric = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#narrowerGeneric" );

    /** <p>An extension of the 'broader' property to specify a partitive (part of) relationship
     *  between two concepts.</p>
     */
    public static final Property broaderPartitive = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#broaderPartitive" );

    /** <p>An extension of the 'broader' property to specify the instantiation (instance
     *  of) relationship between two concepts. This property is semantically equivalent
     *  to the 'rdf:type' property.</p>
     */
    public static final Property broaderInstantive = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#broaderInstantive" );

    /** <p>This property is the inverse of the 'relatedPartOf' property.</p> */
    public static final Property relatedHasPart = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#relatedHasPart" );

    /** <p>Use or extend this property to indicate any non-URI code that is used to uniquely
     *  identify a concept within a conceptual scheme.</p>
     */
    public static final Property externalID = m_model.createProperty( "http://www.w3.org/2004/02/skos/core#externalID" );

    public static final Resource Concept = m_model.createResource( "http://www.w3.org/2004/02/skos/core#Concept" );

    /** <p>Thesauri, classification schemes, subject heading lists, taxonomies, 'folksonomies',
     *  and other types of controlled vocabulary are all examples of concept schemes.
     *  Concept schemes are also embedded in glossaries and terminologies.A concept
     *  scheme may be defined to include concepts from different sources.</p>
     */
    public static final Resource ConceptScheme = m_model.createResource( "http://www.w3.org/2004/02/skos/core#ConceptScheme" );

    /** <p>Labelled collections can be used with collectable semantic relation properties
     *  e.g. skos:narrower, where you would like a set of concepts to be displayed
     *  under a 'node label' in the hierarchy.</p>
     */
    public static final Resource Collection = m_model.createResource( "http://www.w3.org/2004/02/skos/core#Collection" );

    /** <p>Ordered collections can be used with collectable semantic relation properties,
     *  where you would like a set of concepts to be displayed in a specific order,
     *  and optionally under a 'node label'.</p>
     */
    public static final Resource OrderedCollection = m_model.createResource( "http://www.w3.org/2004/02/skos/core#OrderedCollection" );

    /** <p>The following rule applies for this property: [(?x ?p ?c) (?c skos:member
     *  ?y) (?p rdf:type skos:CollectableProperty) implies (?x ?p ?y)]</p>
     */
    public static final Resource CollectableProperty = m_model.createResource( "http://www.w3.org/2004/02/skos/core#CollectableProperty" );

}
