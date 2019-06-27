/**
 * (C) Copyright IBM Corp. 2019
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.ibm.watsonhealth.fhir.model.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Generated;

import com.ibm.watsonhealth.fhir.model.annotation.Constraint;
import com.ibm.watsonhealth.fhir.model.type.AssertionDirectionType;
import com.ibm.watsonhealth.fhir.model.type.AssertionOperatorType;
import com.ibm.watsonhealth.fhir.model.type.AssertionResponseTypes;
import com.ibm.watsonhealth.fhir.model.type.BackboneElement;
import com.ibm.watsonhealth.fhir.model.type.Boolean;
import com.ibm.watsonhealth.fhir.model.type.Canonical;
import com.ibm.watsonhealth.fhir.model.type.Code;
import com.ibm.watsonhealth.fhir.model.type.CodeableConcept;
import com.ibm.watsonhealth.fhir.model.type.Coding;
import com.ibm.watsonhealth.fhir.model.type.ContactDetail;
import com.ibm.watsonhealth.fhir.model.type.DateTime;
import com.ibm.watsonhealth.fhir.model.type.Extension;
import com.ibm.watsonhealth.fhir.model.type.FHIRDefinedType;
import com.ibm.watsonhealth.fhir.model.type.Id;
import com.ibm.watsonhealth.fhir.model.type.Identifier;
import com.ibm.watsonhealth.fhir.model.type.Integer;
import com.ibm.watsonhealth.fhir.model.type.Markdown;
import com.ibm.watsonhealth.fhir.model.type.Meta;
import com.ibm.watsonhealth.fhir.model.type.Narrative;
import com.ibm.watsonhealth.fhir.model.type.PublicationStatus;
import com.ibm.watsonhealth.fhir.model.type.Reference;
import com.ibm.watsonhealth.fhir.model.type.String;
import com.ibm.watsonhealth.fhir.model.type.TestScriptRequestMethodCode;
import com.ibm.watsonhealth.fhir.model.type.Uri;
import com.ibm.watsonhealth.fhir.model.type.UsageContext;
import com.ibm.watsonhealth.fhir.model.util.ValidationSupport;
import com.ibm.watsonhealth.fhir.model.visitor.Visitor;

/**
 * <p>
 * A structured set of tests against a FHIR server or client implementation to determine compliance against the FHIR 
 * specification.
 * </p>
 */
@Constraint(
    key = "tst-0",
    severity = "warning",
    human = "Name should be usable as an identifier for the module by machine processing applications such as code generation",
    expression = "name.matches('[A-Z]([A-Za-z0-9_]){0,254}')"
)
@Generated("com.ibm.watsonhealth.fhir.tools.CodeGenerator")
public class TestScript extends DomainResource {
    private final Uri url;
    private final Identifier identifier;
    private final String version;
    private final String name;
    private final String title;
    private final PublicationStatus status;
    private final Boolean experimental;
    private final DateTime date;
    private final String publisher;
    private final List<ContactDetail> contact;
    private final Markdown description;
    private final List<UsageContext> useContext;
    private final List<CodeableConcept> jurisdiction;
    private final Markdown purpose;
    private final Markdown copyright;
    private final List<Origin> origin;
    private final List<Destination> destination;
    private final Metadata metadata;
    private final List<Fixture> fixture;
    private final List<Reference> profile;
    private final List<Variable> variable;
    private final Setup setup;
    private final List<Test> test;
    private final Teardown teardown;

    private TestScript(Builder builder) {
        super(builder);
        this.url = ValidationSupport.requireNonNull(builder.url, "url");
        this.identifier = builder.identifier;
        this.version = builder.version;
        this.name = ValidationSupport.requireNonNull(builder.name, "name");
        this.title = builder.title;
        this.status = ValidationSupport.requireNonNull(builder.status, "status");
        this.experimental = builder.experimental;
        this.date = builder.date;
        this.publisher = builder.publisher;
        this.contact = builder.contact;
        this.description = builder.description;
        this.useContext = builder.useContext;
        this.jurisdiction = builder.jurisdiction;
        this.purpose = builder.purpose;
        this.copyright = builder.copyright;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.metadata = builder.metadata;
        this.fixture = builder.fixture;
        this.profile = builder.profile;
        this.variable = builder.variable;
        this.setup = builder.setup;
        this.test = builder.test;
        this.teardown = builder.teardown;
    }

    /**
     * <p>
     * An absolute URI that is used to identify this test script when it is referenced in a specification, model, design or 
     * an instance; also called its canonical identifier. This SHOULD be globally unique and SHOULD be a literal address at 
     * which at which an authoritative instance of this test script is (or will be) published. This URL can be the target of 
     * a canonical reference. It SHALL remain the same when the test script is stored on different servers.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Uri}.
     */
    public Uri getUrl() {
        return url;
    }

    /**
     * <p>
     * A formal identifier that is used to identify this test script when it is represented in other formats, or referenced 
     * in a specification, model, design or an instance.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Identifier}.
     */
    public Identifier getIdentifier() {
        return identifier;
    }

    /**
     * <p>
     * The identifier that is used to identify this version of the test script when it is referenced in a specification, 
     * model, design or instance. This is an arbitrary value managed by the test script author and is not expected to be 
     * globally unique. For example, it might be a timestamp (e.g. yyyymmdd) if a managed version is not available. There is 
     * also no expectation that versions can be placed in a lexicographical sequence.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getVersion() {
        return version;
    }

    /**
     * <p>
     * A natural language name identifying the test script. This name should be usable as an identifier for the module by 
     * machine processing applications such as code generation.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getName() {
        return name;
    }

    /**
     * <p>
     * A short, descriptive, user-friendly title for the test script.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>
     * The status of this test script. Enables tracking the life-cycle of the content.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link PublicationStatus}.
     */
    public PublicationStatus getStatus() {
        return status;
    }

    /**
     * <p>
     * A Boolean value to indicate that this test script is authored for testing purposes (or education/evaluation/marketing) 
     * and is not intended to be used for genuine usage.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Boolean}.
     */
    public Boolean getExperimental() {
        return experimental;
    }

    /**
     * <p>
     * The date (and optionally time) when the test script was published. The date must change when the business version 
     * changes and it must change if the status code changes. In addition, it should change when the substantive content of 
     * the test script changes.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link DateTime}.
     */
    public DateTime getDate() {
        return date;
    }

    /**
     * <p>
     * The name of the organization or individual that published the test script.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link String}.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * <p>
     * Contact details to assist a user in finding and communicating with the publisher.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link ContactDetail}.
     */
    public List<ContactDetail> getContact() {
        return contact;
    }

    /**
     * <p>
     * A free text natural language description of the test script from a consumer's perspective.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getDescription() {
        return description;
    }

    /**
     * <p>
     * The content was developed with a focus and intent of supporting the contexts that are listed. These contexts may be 
     * general categories (gender, age, ...) or may be references to specific programs (insurance plans, studies, ...) and 
     * may be used to assist with indexing and searching for appropriate test script instances.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link UsageContext}.
     */
    public List<UsageContext> getUseContext() {
        return useContext;
    }

    /**
     * <p>
     * A legal or geographic region in which the test script is intended to be used.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link CodeableConcept}.
     */
    public List<CodeableConcept> getJurisdiction() {
        return jurisdiction;
    }

    /**
     * <p>
     * Explanation of why this test script is needed and why it has been designed as it has.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getPurpose() {
        return purpose;
    }

    /**
     * <p>
     * A copyright statement relating to the test script and/or its contents. Copyright statements are generally legal 
     * restrictions on the use and publishing of the test script.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Markdown}.
     */
    public Markdown getCopyright() {
        return copyright;
    }

    /**
     * <p>
     * An abstract server used in operations within this test script in the origin element.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Origin}.
     */
    public List<Origin> getOrigin() {
        return origin;
    }

    /**
     * <p>
     * An abstract server used in operations within this test script in the destination element.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Destination}.
     */
    public List<Destination> getDestination() {
        return destination;
    }

    /**
     * <p>
     * The required capability must exist and are assumed to function correctly on the FHIR server being tested.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Metadata}.
     */
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * <p>
     * Fixture in the test script - by reference (uri). All fixtures are required for the test script to execute.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Fixture}.
     */
    public List<Fixture> getFixture() {
        return fixture;
    }

    /**
     * <p>
     * Reference to the profile to be used for validation.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Reference}.
     */
    public List<Reference> getProfile() {
        return profile;
    }

    /**
     * <p>
     * Variable is set based either on element value in response body or on header field value in the response headers.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Variable}.
     */
    public List<Variable> getVariable() {
        return variable;
    }

    /**
     * <p>
     * A series of required setup operations before tests are executed.
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Setup}.
     */
    public Setup getSetup() {
        return setup;
    }

    /**
     * <p>
     * A test in this script.
     * </p>
     * 
     * @return
     *     A list containing immutable objects of type {@link Test}.
     */
    public List<Test> getTest() {
        return test;
    }

    /**
     * <p>
     * A series of operations required to clean up after all the tests are executed (successfully or otherwise).
     * </p>
     * 
     * @return
     *     An immutable object of type {@link Teardown}.
     */
    public Teardown getTeardown() {
        return teardown;
    }

    @Override
    public void accept(java.lang.String elementName, Visitor visitor) {
        if (visitor.preVisit(this)) {
            visitor.visitStart(elementName, this);
            if (visitor.visit(elementName, this)) {
                // visit children
                accept(id, "id", visitor);
                accept(meta, "meta", visitor);
                accept(implicitRules, "implicitRules", visitor);
                accept(language, "language", visitor);
                accept(text, "text", visitor);
                accept(contained, "contained", visitor, Resource.class);
                accept(extension, "extension", visitor, Extension.class);
                accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                accept(url, "url", visitor);
                accept(identifier, "identifier", visitor);
                accept(version, "version", visitor);
                accept(name, "name", visitor);
                accept(title, "title", visitor);
                accept(status, "status", visitor);
                accept(experimental, "experimental", visitor);
                accept(date, "date", visitor);
                accept(publisher, "publisher", visitor);
                accept(contact, "contact", visitor, ContactDetail.class);
                accept(description, "description", visitor);
                accept(useContext, "useContext", visitor, UsageContext.class);
                accept(jurisdiction, "jurisdiction", visitor, CodeableConcept.class);
                accept(purpose, "purpose", visitor);
                accept(copyright, "copyright", visitor);
                accept(origin, "origin", visitor, Origin.class);
                accept(destination, "destination", visitor, Destination.class);
                accept(metadata, "metadata", visitor);
                accept(fixture, "fixture", visitor, Fixture.class);
                accept(profile, "profile", visitor, Reference.class);
                accept(variable, "variable", visitor, Variable.class);
                accept(setup, "setup", visitor);
                accept(test, "test", visitor, Test.class);
                accept(teardown, "teardown", visitor);
            }
            visitor.visitEnd(elementName, this);
            visitor.postVisit(this);
        }
    }

    @Override
    public Builder toBuilder() {
        Builder builder = new Builder(url, name, status);
        builder.id = id;
        builder.meta = meta;
        builder.implicitRules = implicitRules;
        builder.language = language;
        builder.text = text;
        builder.contained.addAll(contained);
        builder.extension.addAll(extension);
        builder.modifierExtension.addAll(modifierExtension);
        builder.identifier = identifier;
        builder.version = version;
        builder.title = title;
        builder.experimental = experimental;
        builder.date = date;
        builder.publisher = publisher;
        builder.contact.addAll(contact);
        builder.description = description;
        builder.useContext.addAll(useContext);
        builder.jurisdiction.addAll(jurisdiction);
        builder.purpose = purpose;
        builder.copyright = copyright;
        builder.origin.addAll(origin);
        builder.destination.addAll(destination);
        builder.metadata = metadata;
        builder.fixture.addAll(fixture);
        builder.profile.addAll(profile);
        builder.variable.addAll(variable);
        builder.setup = setup;
        builder.test.addAll(test);
        builder.teardown = teardown;
        return builder;
    }

    public static Builder builder(Uri url, String name, PublicationStatus status) {
        return new Builder(url, name, status);
    }

    public static class Builder extends DomainResource.Builder {
        // required
        private final Uri url;
        private final String name;
        private final PublicationStatus status;

        // optional
        private Identifier identifier;
        private String version;
        private String title;
        private Boolean experimental;
        private DateTime date;
        private String publisher;
        private List<ContactDetail> contact = new ArrayList<>();
        private Markdown description;
        private List<UsageContext> useContext = new ArrayList<>();
        private List<CodeableConcept> jurisdiction = new ArrayList<>();
        private Markdown purpose;
        private Markdown copyright;
        private List<Origin> origin = new ArrayList<>();
        private List<Destination> destination = new ArrayList<>();
        private Metadata metadata;
        private List<Fixture> fixture = new ArrayList<>();
        private List<Reference> profile = new ArrayList<>();
        private List<Variable> variable = new ArrayList<>();
        private Setup setup;
        private List<Test> test = new ArrayList<>();
        private Teardown teardown;

        private Builder(Uri url, String name, PublicationStatus status) {
            super();
            this.url = url;
            this.name = name;
            this.status = status;
        }

        /**
         * <p>
         * The logical id of the resource, as used in the URL for the resource. Once assigned, this value never changes.
         * </p>
         * 
         * @param id
         *     Logical id of this artifact
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder id(Id id) {
            return (Builder) super.id(id);
        }

        /**
         * <p>
         * The metadata about the resource. This is content that is maintained by the infrastructure. Changes to the content 
         * might not always be associated with version changes to the resource.
         * </p>
         * 
         * @param meta
         *     Metadata about the resource
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder meta(Meta meta) {
            return (Builder) super.meta(meta);
        }

        /**
         * <p>
         * A reference to a set of rules that were followed when the resource was constructed, and which must be understood when 
         * processing the content. Often, this is a reference to an implementation guide that defines the special rules along 
         * with other profiles etc.
         * </p>
         * 
         * @param implicitRules
         *     A set of rules under which this content was created
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder implicitRules(Uri implicitRules) {
            return (Builder) super.implicitRules(implicitRules);
        }

        /**
         * <p>
         * The base language in which the resource is written.
         * </p>
         * 
         * @param language
         *     Language of the resource content
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder language(Code language) {
            return (Builder) super.language(language);
        }

        /**
         * <p>
         * A human-readable narrative that contains a summary of the resource and can be used to represent the content of the 
         * resource to a human. The narrative need not encode all the structured data, but is required to contain sufficient 
         * detail to make it "clinically safe" for a human to just read the narrative. Resource definitions may define what 
         * content should be represented in the narrative to ensure clinical safety.
         * </p>
         * 
         * @param text
         *     Text summary of the resource, for human interpretation
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder text(Narrative text) {
            return (Builder) super.text(text);
        }

        /**
         * <p>
         * These resources do not have an independent existence apart from the resource that contains them - they cannot be 
         * identified independently, and nor can they have their own independent transaction scope.
         * </p>
         * 
         * @param contained
         *     Contained, inline Resources
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder contained(Resource... contained) {
            return (Builder) super.contained(contained);
        }

        /**
         * <p>
         * These resources do not have an independent existence apart from the resource that contains them - they cannot be 
         * identified independently, and nor can they have their own independent transaction scope.
         * </p>
         * 
         * @param contained
         *     Contained, inline Resources
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder contained(Collection<Resource> contained) {
            return (Builder) super.contained(contained);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource. To make the 
         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
         * of the definition of the extension.
         * </p>
         * 
         * @param extension
         *     Additional content defined by implementations
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder extension(Extension... extension) {
            return (Builder) super.extension(extension);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource. To make the 
         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
         * of the definition of the extension.
         * </p>
         * 
         * @param extension
         *     Additional content defined by implementations
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder extension(Collection<Extension> extension) {
            return (Builder) super.extension(extension);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource and that 
         * modifies the understanding of the element that contains it and/or the understanding of the containing element's 
         * descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe and 
         * manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
         * implementer is allowed to define an extension, there is a set of requirements that SHALL be met as part of the 
         * definition of the extension. Applications processing a resource are required to check for modifier extensions.
         * </p>
         * <p>
         * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
         * change the meaning of modifierExtension itself).
         * </p>
         * 
         * @param modifierExtension
         *     Extensions that cannot be ignored
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder modifierExtension(Extension... modifierExtension) {
            return (Builder) super.modifierExtension(modifierExtension);
        }

        /**
         * <p>
         * May be used to represent additional information that is not part of the basic definition of the resource and that 
         * modifies the understanding of the element that contains it and/or the understanding of the containing element's 
         * descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe and 
         * manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
         * implementer is allowed to define an extension, there is a set of requirements that SHALL be met as part of the 
         * definition of the extension. Applications processing a resource are required to check for modifier extensions.
         * </p>
         * <p>
         * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
         * change the meaning of modifierExtension itself).
         * </p>
         * 
         * @param modifierExtension
         *     Extensions that cannot be ignored
         * 
         * @return
         *     A reference to this Builder instance.
         */
        @Override
        public Builder modifierExtension(Collection<Extension> modifierExtension) {
            return (Builder) super.modifierExtension(modifierExtension);
        }

        /**
         * <p>
         * A formal identifier that is used to identify this test script when it is represented in other formats, or referenced 
         * in a specification, model, design or an instance.
         * </p>
         * 
         * @param identifier
         *     Additional identifier for the test script
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * <p>
         * The identifier that is used to identify this version of the test script when it is referenced in a specification, 
         * model, design or instance. This is an arbitrary value managed by the test script author and is not expected to be 
         * globally unique. For example, it might be a timestamp (e.g. yyyymmdd) if a managed version is not available. There is 
         * also no expectation that versions can be placed in a lexicographical sequence.
         * </p>
         * 
         * @param version
         *     Business version of the test script
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder version(String version) {
            this.version = version;
            return this;
        }

        /**
         * <p>
         * A short, descriptive, user-friendly title for the test script.
         * </p>
         * 
         * @param title
         *     Name for this test script (human friendly)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder title(String title) {
            this.title = title;
            return this;
        }

        /**
         * <p>
         * A Boolean value to indicate that this test script is authored for testing purposes (or education/evaluation/marketing) 
         * and is not intended to be used for genuine usage.
         * </p>
         * 
         * @param experimental
         *     For testing purposes, not real usage
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder experimental(Boolean experimental) {
            this.experimental = experimental;
            return this;
        }

        /**
         * <p>
         * The date (and optionally time) when the test script was published. The date must change when the business version 
         * changes and it must change if the status code changes. In addition, it should change when the substantive content of 
         * the test script changes.
         * </p>
         * 
         * @param date
         *     Date last changed
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder date(DateTime date) {
            this.date = date;
            return this;
        }

        /**
         * <p>
         * The name of the organization or individual that published the test script.
         * </p>
         * 
         * @param publisher
         *     Name of the publisher (organization or individual)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        /**
         * <p>
         * Contact details to assist a user in finding and communicating with the publisher.
         * </p>
         * 
         * @param contact
         *     Contact details for the publisher
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder contact(ContactDetail... contact) {
            for (ContactDetail value : contact) {
                this.contact.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Contact details to assist a user in finding and communicating with the publisher.
         * </p>
         * 
         * @param contact
         *     Contact details for the publisher
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder contact(Collection<ContactDetail> contact) {
            this.contact.addAll(contact);
            return this;
        }

        /**
         * <p>
         * A free text natural language description of the test script from a consumer's perspective.
         * </p>
         * 
         * @param description
         *     Natural language description of the test script
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder description(Markdown description) {
            this.description = description;
            return this;
        }

        /**
         * <p>
         * The content was developed with a focus and intent of supporting the contexts that are listed. These contexts may be 
         * general categories (gender, age, ...) or may be references to specific programs (insurance plans, studies, ...) and 
         * may be used to assist with indexing and searching for appropriate test script instances.
         * </p>
         * 
         * @param useContext
         *     The context that the content is intended to support
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder useContext(UsageContext... useContext) {
            for (UsageContext value : useContext) {
                this.useContext.add(value);
            }
            return this;
        }

        /**
         * <p>
         * The content was developed with a focus and intent of supporting the contexts that are listed. These contexts may be 
         * general categories (gender, age, ...) or may be references to specific programs (insurance plans, studies, ...) and 
         * may be used to assist with indexing and searching for appropriate test script instances.
         * </p>
         * 
         * @param useContext
         *     The context that the content is intended to support
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder useContext(Collection<UsageContext> useContext) {
            this.useContext.addAll(useContext);
            return this;
        }

        /**
         * <p>
         * A legal or geographic region in which the test script is intended to be used.
         * </p>
         * 
         * @param jurisdiction
         *     Intended jurisdiction for test script (if applicable)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder jurisdiction(CodeableConcept... jurisdiction) {
            for (CodeableConcept value : jurisdiction) {
                this.jurisdiction.add(value);
            }
            return this;
        }

        /**
         * <p>
         * A legal or geographic region in which the test script is intended to be used.
         * </p>
         * 
         * @param jurisdiction
         *     Intended jurisdiction for test script (if applicable)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder jurisdiction(Collection<CodeableConcept> jurisdiction) {
            this.jurisdiction.addAll(jurisdiction);
            return this;
        }

        /**
         * <p>
         * Explanation of why this test script is needed and why it has been designed as it has.
         * </p>
         * 
         * @param purpose
         *     Why this test script is defined
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder purpose(Markdown purpose) {
            this.purpose = purpose;
            return this;
        }

        /**
         * <p>
         * A copyright statement relating to the test script and/or its contents. Copyright statements are generally legal 
         * restrictions on the use and publishing of the test script.
         * </p>
         * 
         * @param copyright
         *     Use and/or publishing restrictions
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder copyright(Markdown copyright) {
            this.copyright = copyright;
            return this;
        }

        /**
         * <p>
         * An abstract server used in operations within this test script in the origin element.
         * </p>
         * 
         * @param origin
         *     An abstract server representing a client or sender in a message exchange
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder origin(Origin... origin) {
            for (Origin value : origin) {
                this.origin.add(value);
            }
            return this;
        }

        /**
         * <p>
         * An abstract server used in operations within this test script in the origin element.
         * </p>
         * 
         * @param origin
         *     An abstract server representing a client or sender in a message exchange
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder origin(Collection<Origin> origin) {
            this.origin.addAll(origin);
            return this;
        }

        /**
         * <p>
         * An abstract server used in operations within this test script in the destination element.
         * </p>
         * 
         * @param destination
         *     An abstract server representing a destination or receiver in a message exchange
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder destination(Destination... destination) {
            for (Destination value : destination) {
                this.destination.add(value);
            }
            return this;
        }

        /**
         * <p>
         * An abstract server used in operations within this test script in the destination element.
         * </p>
         * 
         * @param destination
         *     An abstract server representing a destination or receiver in a message exchange
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder destination(Collection<Destination> destination) {
            this.destination.addAll(destination);
            return this;
        }

        /**
         * <p>
         * The required capability must exist and are assumed to function correctly on the FHIR server being tested.
         * </p>
         * 
         * @param metadata
         *     Required capability that is assumed to function correctly on the FHIR server being tested
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder metadata(Metadata metadata) {
            this.metadata = metadata;
            return this;
        }

        /**
         * <p>
         * Fixture in the test script - by reference (uri). All fixtures are required for the test script to execute.
         * </p>
         * 
         * @param fixture
         *     Fixture in the test script - by reference (uri)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder fixture(Fixture... fixture) {
            for (Fixture value : fixture) {
                this.fixture.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Fixture in the test script - by reference (uri). All fixtures are required for the test script to execute.
         * </p>
         * 
         * @param fixture
         *     Fixture in the test script - by reference (uri)
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder fixture(Collection<Fixture> fixture) {
            this.fixture.addAll(fixture);
            return this;
        }

        /**
         * <p>
         * Reference to the profile to be used for validation.
         * </p>
         * 
         * @param profile
         *     Reference of the validation profile
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder profile(Reference... profile) {
            for (Reference value : profile) {
                this.profile.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Reference to the profile to be used for validation.
         * </p>
         * 
         * @param profile
         *     Reference of the validation profile
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder profile(Collection<Reference> profile) {
            this.profile.addAll(profile);
            return this;
        }

        /**
         * <p>
         * Variable is set based either on element value in response body or on header field value in the response headers.
         * </p>
         * 
         * @param variable
         *     Placeholder for evaluated elements
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder variable(Variable... variable) {
            for (Variable value : variable) {
                this.variable.add(value);
            }
            return this;
        }

        /**
         * <p>
         * Variable is set based either on element value in response body or on header field value in the response headers.
         * </p>
         * 
         * @param variable
         *     Placeholder for evaluated elements
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder variable(Collection<Variable> variable) {
            this.variable.addAll(variable);
            return this;
        }

        /**
         * <p>
         * A series of required setup operations before tests are executed.
         * </p>
         * 
         * @param setup
         *     A series of required setup operations before tests are executed
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder setup(Setup setup) {
            this.setup = setup;
            return this;
        }

        /**
         * <p>
         * A test in this script.
         * </p>
         * 
         * @param test
         *     A test in this script
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder test(Test... test) {
            for (Test value : test) {
                this.test.add(value);
            }
            return this;
        }

        /**
         * <p>
         * A test in this script.
         * </p>
         * 
         * @param test
         *     A test in this script
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder test(Collection<Test> test) {
            this.test.addAll(test);
            return this;
        }

        /**
         * <p>
         * A series of operations required to clean up after all the tests are executed (successfully or otherwise).
         * </p>
         * 
         * @param teardown
         *     A series of required clean up steps
         * 
         * @return
         *     A reference to this Builder instance.
         */
        public Builder teardown(Teardown teardown) {
            this.teardown = teardown;
            return this;
        }

        @Override
        public TestScript build() {
            return new TestScript(this);
        }
    }

    /**
     * <p>
     * An abstract server used in operations within this test script in the origin element.
     * </p>
     */
    public static class Origin extends BackboneElement {
        private final Integer index;
        private final Coding profile;

        private Origin(Builder builder) {
            super(builder);
            this.index = ValidationSupport.requireNonNull(builder.index, "index");
            this.profile = ValidationSupport.requireNonNull(builder.profile, "profile");
        }

        /**
         * <p>
         * Abstract name given to an origin server in this test script. The name is provided as a number starting at 1.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Integer}.
         */
        public Integer getIndex() {
            return index;
        }

        /**
         * <p>
         * The type of origin profile the test system supports.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Coding}.
         */
        public Coding getProfile() {
            return profile;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(index, "index", visitor);
                    accept(profile, "profile", visitor);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return Builder.from(this);
        }

        public static Builder builder(Integer index, Coding profile) {
            return new Builder(index, profile);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final Integer index;
            private final Coding profile;

            private Builder(Integer index, Coding profile) {
                super();
                this.index = index;
                this.profile = profile;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            @Override
            public Origin build() {
                return new Origin(this);
            }

            private static Builder from(Origin origin) {
                Builder builder = new Builder(origin.index, origin.profile);
                builder.id = origin.id;
                builder.extension.addAll(origin.extension);
                builder.modifierExtension.addAll(origin.modifierExtension);
                return builder;
            }
        }
    }

    /**
     * <p>
     * An abstract server used in operations within this test script in the destination element.
     * </p>
     */
    public static class Destination extends BackboneElement {
        private final Integer index;
        private final Coding profile;

        private Destination(Builder builder) {
            super(builder);
            this.index = ValidationSupport.requireNonNull(builder.index, "index");
            this.profile = ValidationSupport.requireNonNull(builder.profile, "profile");
        }

        /**
         * <p>
         * Abstract name given to a destination server in this test script. The name is provided as a number starting at 1.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Integer}.
         */
        public Integer getIndex() {
            return index;
        }

        /**
         * <p>
         * The type of destination profile the test system supports.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Coding}.
         */
        public Coding getProfile() {
            return profile;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(index, "index", visitor);
                    accept(profile, "profile", visitor);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return Builder.from(this);
        }

        public static Builder builder(Integer index, Coding profile) {
            return new Builder(index, profile);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final Integer index;
            private final Coding profile;

            private Builder(Integer index, Coding profile) {
                super();
                this.index = index;
                this.profile = profile;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            @Override
            public Destination build() {
                return new Destination(this);
            }

            private static Builder from(Destination destination) {
                Builder builder = new Builder(destination.index, destination.profile);
                builder.id = destination.id;
                builder.extension.addAll(destination.extension);
                builder.modifierExtension.addAll(destination.modifierExtension);
                return builder;
            }
        }
    }

    /**
     * <p>
     * The required capability must exist and are assumed to function correctly on the FHIR server being tested.
     * </p>
     */
    public static class Metadata extends BackboneElement {
        private final List<Link> link;
        private final List<Capability> capability;

        private Metadata(Builder builder) {
            super(builder);
            this.link = builder.link;
            this.capability = ValidationSupport.requireNonEmpty(builder.capability, "capability");
        }

        /**
         * <p>
         * A link to the FHIR specification that this test is covering.
         * </p>
         * 
         * @return
         *     A list containing immutable objects of type {@link Link}.
         */
        public List<Link> getLink() {
            return link;
        }

        /**
         * <p>
         * Capabilities that must exist and are assumed to function correctly on the FHIR server being tested.
         * </p>
         * 
         * @return
         *     A list containing immutable objects of type {@link Capability}.
         */
        public List<Capability> getCapability() {
            return capability;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(link, "link", visitor, Link.class);
                    accept(capability, "capability", visitor, Capability.class);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return Builder.from(this);
        }

        public static Builder builder(List<Capability> capability) {
            return new Builder(capability);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final List<Capability> capability;

            // optional
            private List<Link> link = new ArrayList<>();

            private Builder(List<Capability> capability) {
                super();
                this.capability = capability;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * A link to the FHIR specification that this test is covering.
             * </p>
             * 
             * @param link
             *     Links to the FHIR specification
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder link(Link... link) {
                for (Link value : link) {
                    this.link.add(value);
                }
                return this;
            }

            /**
             * <p>
             * A link to the FHIR specification that this test is covering.
             * </p>
             * 
             * @param link
             *     Links to the FHIR specification
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder link(Collection<Link> link) {
                this.link.addAll(link);
                return this;
            }

            @Override
            public Metadata build() {
                return new Metadata(this);
            }

            private static Builder from(Metadata metadata) {
                Builder builder = new Builder(metadata.capability);
                builder.id = metadata.id;
                builder.extension.addAll(metadata.extension);
                builder.modifierExtension.addAll(metadata.modifierExtension);
                builder.link.addAll(metadata.link);
                return builder;
            }
        }

        /**
         * <p>
         * A link to the FHIR specification that this test is covering.
         * </p>
         */
        public static class Link extends BackboneElement {
            private final Uri url;
            private final String description;

            private Link(Builder builder) {
                super(builder);
                this.url = ValidationSupport.requireNonNull(builder.url, "url");
                this.description = builder.description;
            }

            /**
             * <p>
             * URL to a particular requirement or feature within the FHIR specification.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Uri}.
             */
            public Uri getUrl() {
                return url;
            }

            /**
             * <p>
             * Short description of the link.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link String}.
             */
            public String getDescription() {
                return description;
            }

            @Override
            public void accept(java.lang.String elementName, Visitor visitor) {
                if (visitor.preVisit(this)) {
                    visitor.visitStart(elementName, this);
                    if (visitor.visit(elementName, this)) {
                        // visit children
                        accept(id, "id", visitor);
                        accept(extension, "extension", visitor, Extension.class);
                        accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                        accept(url, "url", visitor);
                        accept(description, "description", visitor);
                    }
                    visitor.visitEnd(elementName, this);
                    visitor.postVisit(this);
                }
            }

            @Override
            public Builder toBuilder() {
                return Builder.from(this);
            }

            public static Builder builder(Uri url) {
                return new Builder(url);
            }

            public static class Builder extends BackboneElement.Builder {
                // required
                private final Uri url;

                // optional
                private String description;

                private Builder(Uri url) {
                    super();
                    this.url = url;
                }

                /**
                 * <p>
                 * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                 * contain spaces.
                 * </p>
                 * 
                 * @param id
                 *     Unique id for inter-element referencing
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder id(java.lang.String id) {
                    return (Builder) super.id(id);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Extension... extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Collection<Extension> extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Extension... modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Collection<Extension> modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * Short description of the link.
                 * </p>
                 * 
                 * @param description
                 *     Short description
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder description(String description) {
                    this.description = description;
                    return this;
                }

                @Override
                public Link build() {
                    return new Link(this);
                }

                private static Builder from(Link link) {
                    Builder builder = new Builder(link.url);
                    builder.id = link.id;
                    builder.extension.addAll(link.extension);
                    builder.modifierExtension.addAll(link.modifierExtension);
                    builder.description = link.description;
                    return builder;
                }
            }
        }

        /**
         * <p>
         * Capabilities that must exist and are assumed to function correctly on the FHIR server being tested.
         * </p>
         */
        public static class Capability extends BackboneElement {
            private final Boolean required;
            private final Boolean validated;
            private final String description;
            private final List<Integer> origin;
            private final Integer destination;
            private final List<Uri> link;
            private final Canonical capabilities;

            private Capability(Builder builder) {
                super(builder);
                this.required = ValidationSupport.requireNonNull(builder.required, "required");
                this.validated = ValidationSupport.requireNonNull(builder.validated, "validated");
                this.description = builder.description;
                this.origin = builder.origin;
                this.destination = builder.destination;
                this.link = builder.link;
                this.capabilities = ValidationSupport.requireNonNull(builder.capabilities, "capabilities");
            }

            /**
             * <p>
             * Whether or not the test execution will require the given capabilities of the server in order for this test script to 
             * execute.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Boolean}.
             */
            public Boolean getRequired() {
                return required;
            }

            /**
             * <p>
             * Whether or not the test execution will validate the given capabilities of the server in order for this test script to 
             * execute.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Boolean}.
             */
            public Boolean getValidated() {
                return validated;
            }

            /**
             * <p>
             * Description of the capabilities that this test script is requiring the server to support.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link String}.
             */
            public String getDescription() {
                return description;
            }

            /**
             * <p>
             * Which origin server these requirements apply to.
             * </p>
             * 
             * @return
             *     A list containing immutable objects of type {@link Integer}.
             */
            public List<Integer> getOrigin() {
                return origin;
            }

            /**
             * <p>
             * Which server these requirements apply to.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Integer}.
             */
            public Integer getDestination() {
                return destination;
            }

            /**
             * <p>
             * Links to the FHIR specification that describes this interaction and the resources involved in more detail.
             * </p>
             * 
             * @return
             *     A list containing immutable objects of type {@link Uri}.
             */
            public List<Uri> getLink() {
                return link;
            }

            /**
             * <p>
             * Minimum capabilities required of server for test script to execute successfully. If server does not meet at a minimum 
             * the referenced capability statement, then all tests in this script are skipped.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Canonical}.
             */
            public Canonical getCapabilities() {
                return capabilities;
            }

            @Override
            public void accept(java.lang.String elementName, Visitor visitor) {
                if (visitor.preVisit(this)) {
                    visitor.visitStart(elementName, this);
                    if (visitor.visit(elementName, this)) {
                        // visit children
                        accept(id, "id", visitor);
                        accept(extension, "extension", visitor, Extension.class);
                        accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                        accept(required, "required", visitor);
                        accept(validated, "validated", visitor);
                        accept(description, "description", visitor);
                        accept(origin, "origin", visitor, Integer.class);
                        accept(destination, "destination", visitor);
                        accept(link, "link", visitor, Uri.class);
                        accept(capabilities, "capabilities", visitor);
                    }
                    visitor.visitEnd(elementName, this);
                    visitor.postVisit(this);
                }
            }

            @Override
            public Builder toBuilder() {
                return Builder.from(this);
            }

            public static Builder builder(Boolean required, Boolean validated, Canonical capabilities) {
                return new Builder(required, validated, capabilities);
            }

            public static class Builder extends BackboneElement.Builder {
                // required
                private final Boolean required;
                private final Boolean validated;
                private final Canonical capabilities;

                // optional
                private String description;
                private List<Integer> origin = new ArrayList<>();
                private Integer destination;
                private List<Uri> link = new ArrayList<>();

                private Builder(Boolean required, Boolean validated, Canonical capabilities) {
                    super();
                    this.required = required;
                    this.validated = validated;
                    this.capabilities = capabilities;
                }

                /**
                 * <p>
                 * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                 * contain spaces.
                 * </p>
                 * 
                 * @param id
                 *     Unique id for inter-element referencing
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder id(java.lang.String id) {
                    return (Builder) super.id(id);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Extension... extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Collection<Extension> extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Extension... modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Collection<Extension> modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * Description of the capabilities that this test script is requiring the server to support.
                 * </p>
                 * 
                 * @param description
                 *     The expected capabilities of the server
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder description(String description) {
                    this.description = description;
                    return this;
                }

                /**
                 * <p>
                 * Which origin server these requirements apply to.
                 * </p>
                 * 
                 * @param origin
                 *     Which origin server these requirements apply to
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder origin(Integer... origin) {
                    for (Integer value : origin) {
                        this.origin.add(value);
                    }
                    return this;
                }

                /**
                 * <p>
                 * Which origin server these requirements apply to.
                 * </p>
                 * 
                 * @param origin
                 *     Which origin server these requirements apply to
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder origin(Collection<Integer> origin) {
                    this.origin.addAll(origin);
                    return this;
                }

                /**
                 * <p>
                 * Which server these requirements apply to.
                 * </p>
                 * 
                 * @param destination
                 *     Which server these requirements apply to
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder destination(Integer destination) {
                    this.destination = destination;
                    return this;
                }

                /**
                 * <p>
                 * Links to the FHIR specification that describes this interaction and the resources involved in more detail.
                 * </p>
                 * 
                 * @param link
                 *     Links to the FHIR specification
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder link(Uri... link) {
                    for (Uri value : link) {
                        this.link.add(value);
                    }
                    return this;
                }

                /**
                 * <p>
                 * Links to the FHIR specification that describes this interaction and the resources involved in more detail.
                 * </p>
                 * 
                 * @param link
                 *     Links to the FHIR specification
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder link(Collection<Uri> link) {
                    this.link.addAll(link);
                    return this;
                }

                @Override
                public Capability build() {
                    return new Capability(this);
                }

                private static Builder from(Capability capability) {
                    Builder builder = new Builder(capability.required, capability.validated, capability.capabilities);
                    builder.id = capability.id;
                    builder.extension.addAll(capability.extension);
                    builder.modifierExtension.addAll(capability.modifierExtension);
                    builder.description = capability.description;
                    builder.origin.addAll(capability.origin);
                    builder.destination = capability.destination;
                    builder.link.addAll(capability.link);
                    return builder;
                }
            }
        }
    }

    /**
     * <p>
     * Fixture in the test script - by reference (uri). All fixtures are required for the test script to execute.
     * </p>
     */
    public static class Fixture extends BackboneElement {
        private final Boolean autocreate;
        private final Boolean autodelete;
        private final Reference resource;

        private Fixture(Builder builder) {
            super(builder);
            this.autocreate = ValidationSupport.requireNonNull(builder.autocreate, "autocreate");
            this.autodelete = ValidationSupport.requireNonNull(builder.autodelete, "autodelete");
            this.resource = builder.resource;
        }

        /**
         * <p>
         * Whether or not to implicitly create the fixture during setup. If true, the fixture is automatically created on each 
         * server being tested during setup, therefore no create operation is required for this fixture in the TestScript.setup 
         * section.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Boolean}.
         */
        public Boolean getAutocreate() {
            return autocreate;
        }

        /**
         * <p>
         * Whether or not to implicitly delete the fixture during teardown. If true, the fixture is automatically deleted on each 
         * server being tested during teardown, therefore no delete operation is required for this fixture in the TestScript.
         * teardown section.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Boolean}.
         */
        public Boolean getAutodelete() {
            return autodelete;
        }

        /**
         * <p>
         * Reference to the resource (containing the contents of the resource needed for operations).
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Reference}.
         */
        public Reference getResource() {
            return resource;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(autocreate, "autocreate", visitor);
                    accept(autodelete, "autodelete", visitor);
                    accept(resource, "resource", visitor);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return Builder.from(this);
        }

        public static Builder builder(Boolean autocreate, Boolean autodelete) {
            return new Builder(autocreate, autodelete);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final Boolean autocreate;
            private final Boolean autodelete;

            // optional
            private Reference resource;

            private Builder(Boolean autocreate, Boolean autodelete) {
                super();
                this.autocreate = autocreate;
                this.autodelete = autodelete;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * Reference to the resource (containing the contents of the resource needed for operations).
             * </p>
             * 
             * @param resource
             *     Reference of the resource
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder resource(Reference resource) {
                this.resource = resource;
                return this;
            }

            @Override
            public Fixture build() {
                return new Fixture(this);
            }

            private static Builder from(Fixture fixture) {
                Builder builder = new Builder(fixture.autocreate, fixture.autodelete);
                builder.id = fixture.id;
                builder.extension.addAll(fixture.extension);
                builder.modifierExtension.addAll(fixture.modifierExtension);
                builder.resource = fixture.resource;
                return builder;
            }
        }
    }

    /**
     * <p>
     * Variable is set based either on element value in response body or on header field value in the response headers.
     * </p>
     */
    public static class Variable extends BackboneElement {
        private final String name;
        private final String defaultValue;
        private final String description;
        private final String expression;
        private final String headerField;
        private final String hint;
        private final String path;
        private final Id sourceId;

        private Variable(Builder builder) {
            super(builder);
            this.name = ValidationSupport.requireNonNull(builder.name, "name");
            this.defaultValue = builder.defaultValue;
            this.description = builder.description;
            this.expression = builder.expression;
            this.headerField = builder.headerField;
            this.hint = builder.hint;
            this.path = builder.path;
            this.sourceId = builder.sourceId;
        }

        /**
         * <p>
         * Descriptive name for this variable.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getName() {
            return name;
        }

        /**
         * <p>
         * A default, hard-coded, or user-defined value for this variable.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getDefaultValue() {
            return defaultValue;
        }

        /**
         * <p>
         * A free text natural language description of the variable and its purpose.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getDescription() {
            return description;
        }

        /**
         * <p>
         * The FHIRPath expression to evaluate against the fixture body. When variables are defined, only one of either 
         * expression, headerField or path must be specified.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getExpression() {
            return expression;
        }

        /**
         * <p>
         * Will be used to grab the HTTP header field value from the headers that sourceId is pointing to.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getHeaderField() {
            return headerField;
        }

        /**
         * <p>
         * Displayable text string with hint help information to the user when entering a default value.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getHint() {
            return hint;
        }

        /**
         * <p>
         * XPath or JSONPath to evaluate against the fixture body. When variables are defined, only one of either expression, 
         * headerField or path must be specified.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getPath() {
            return path;
        }

        /**
         * <p>
         * Fixture to evaluate the XPath/JSONPath expression or the headerField against within this variable.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link Id}.
         */
        public Id getSourceId() {
            return sourceId;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(name, "name", visitor);
                    accept(defaultValue, "defaultValue", visitor);
                    accept(description, "description", visitor);
                    accept(expression, "expression", visitor);
                    accept(headerField, "headerField", visitor);
                    accept(hint, "hint", visitor);
                    accept(path, "path", visitor);
                    accept(sourceId, "sourceId", visitor);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return Builder.from(this);
        }

        public static Builder builder(String name) {
            return new Builder(name);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final String name;

            // optional
            private String defaultValue;
            private String description;
            private String expression;
            private String headerField;
            private String hint;
            private String path;
            private Id sourceId;

            private Builder(String name) {
                super();
                this.name = name;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * A default, hard-coded, or user-defined value for this variable.
             * </p>
             * 
             * @param defaultValue
             *     Default, hard-coded, or user-defined value for this variable
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder defaultValue(String defaultValue) {
                this.defaultValue = defaultValue;
                return this;
            }

            /**
             * <p>
             * A free text natural language description of the variable and its purpose.
             * </p>
             * 
             * @param description
             *     Natural language description of the variable
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder description(String description) {
                this.description = description;
                return this;
            }

            /**
             * <p>
             * The FHIRPath expression to evaluate against the fixture body. When variables are defined, only one of either 
             * expression, headerField or path must be specified.
             * </p>
             * 
             * @param expression
             *     The FHIRPath expression against the fixture body
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder expression(String expression) {
                this.expression = expression;
                return this;
            }

            /**
             * <p>
             * Will be used to grab the HTTP header field value from the headers that sourceId is pointing to.
             * </p>
             * 
             * @param headerField
             *     HTTP header field name for source
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder headerField(String headerField) {
                this.headerField = headerField;
                return this;
            }

            /**
             * <p>
             * Displayable text string with hint help information to the user when entering a default value.
             * </p>
             * 
             * @param hint
             *     Hint help text for default value to enter
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder hint(String hint) {
                this.hint = hint;
                return this;
            }

            /**
             * <p>
             * XPath or JSONPath to evaluate against the fixture body. When variables are defined, only one of either expression, 
             * headerField or path must be specified.
             * </p>
             * 
             * @param path
             *     XPath or JSONPath against the fixture body
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder path(String path) {
                this.path = path;
                return this;
            }

            /**
             * <p>
             * Fixture to evaluate the XPath/JSONPath expression or the headerField against within this variable.
             * </p>
             * 
             * @param sourceId
             *     Fixture Id of source expression or headerField within this variable
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder sourceId(Id sourceId) {
                this.sourceId = sourceId;
                return this;
            }

            @Override
            public Variable build() {
                return new Variable(this);
            }

            private static Builder from(Variable variable) {
                Builder builder = new Builder(variable.name);
                builder.id = variable.id;
                builder.extension.addAll(variable.extension);
                builder.modifierExtension.addAll(variable.modifierExtension);
                builder.defaultValue = variable.defaultValue;
                builder.description = variable.description;
                builder.expression = variable.expression;
                builder.headerField = variable.headerField;
                builder.hint = variable.hint;
                builder.path = variable.path;
                builder.sourceId = variable.sourceId;
                return builder;
            }
        }
    }

    /**
     * <p>
     * A series of required setup operations before tests are executed.
     * </p>
     */
    public static class Setup extends BackboneElement {
        private final List<Action> action;

        private Setup(Builder builder) {
            super(builder);
            this.action = ValidationSupport.requireNonEmpty(builder.action, "action");
        }

        /**
         * <p>
         * Action would contain either an operation or an assertion.
         * </p>
         * 
         * @return
         *     A list containing immutable objects of type {@link Action}.
         */
        public List<Action> getAction() {
            return action;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(action, "action", visitor, Action.class);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return Builder.from(this);
        }

        public static Builder builder(List<Action> action) {
            return new Builder(action);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final List<Action> action;

            private Builder(List<Action> action) {
                super();
                this.action = action;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            @Override
            public Setup build() {
                return new Setup(this);
            }

            private static Builder from(Setup setup) {
                Builder builder = new Builder(setup.action);
                builder.id = setup.id;
                builder.extension.addAll(setup.extension);
                builder.modifierExtension.addAll(setup.modifierExtension);
                return builder;
            }
        }

        /**
         * <p>
         * Action would contain either an operation or an assertion.
         * </p>
         */
        public static class Action extends BackboneElement {
            private final Operation operation;
            private final Assert _assert;

            private Action(Builder builder) {
                super(builder);
                this.operation = builder.operation;
                this._assert = builder._assert;
            }

            /**
             * <p>
             * The operation to perform.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Operation}.
             */
            public Operation getOperation() {
                return operation;
            }

            /**
             * <p>
             * Evaluates the results of previous operations to determine if the server under test behaves appropriately.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Assert}.
             */
            public Assert getassert() {
                return _assert;
            }

            @Override
            public void accept(java.lang.String elementName, Visitor visitor) {
                if (visitor.preVisit(this)) {
                    visitor.visitStart(elementName, this);
                    if (visitor.visit(elementName, this)) {
                        // visit children
                        accept(id, "id", visitor);
                        accept(extension, "extension", visitor, Extension.class);
                        accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                        accept(operation, "operation", visitor);
                        accept(_assert, "assert", visitor);
                    }
                    visitor.visitEnd(elementName, this);
                    visitor.postVisit(this);
                }
            }

            @Override
            public Builder toBuilder() {
                return Builder.from(this);
            }

            public static Builder builder() {
                return new Builder();
            }

            public static class Builder extends BackboneElement.Builder {
                // optional
                private Operation operation;
                private Assert _assert;

                private Builder() {
                    super();
                }

                /**
                 * <p>
                 * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                 * contain spaces.
                 * </p>
                 * 
                 * @param id
                 *     Unique id for inter-element referencing
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder id(java.lang.String id) {
                    return (Builder) super.id(id);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Extension... extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Collection<Extension> extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Extension... modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Collection<Extension> modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * The operation to perform.
                 * </p>
                 * 
                 * @param operation
                 *     The setup operation to perform
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder operation(Operation operation) {
                    this.operation = operation;
                    return this;
                }

                /**
                 * <p>
                 * Evaluates the results of previous operations to determine if the server under test behaves appropriately.
                 * </p>
                 * 
                 * @param _assert
                 *     The assertion to perform
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder _assert(Assert _assert) {
                    this._assert = _assert;
                    return this;
                }

                @Override
                public Action build() {
                    return new Action(this);
                }

                private static Builder from(Action action) {
                    Builder builder = new Builder();
                    builder.id = action.id;
                    builder.extension.addAll(action.extension);
                    builder.modifierExtension.addAll(action.modifierExtension);
                    builder.operation = action.operation;
                    builder._assert = action._assert;
                    return builder;
                }
            }

            /**
             * <p>
             * The operation to perform.
             * </p>
             */
            public static class Operation extends BackboneElement {
                private final Coding type;
                private final FHIRDefinedType resource;
                private final String label;
                private final String description;
                private final Code accept;
                private final Code contentType;
                private final Integer destination;
                private final Boolean encodeRequestUrl;
                private final TestScriptRequestMethodCode method;
                private final Integer origin;
                private final String params;
                private final List<RequestHeader> requestHeader;
                private final Id requestId;
                private final Id responseId;
                private final Id sourceId;
                private final Id targetId;
                private final String url;

                private Operation(Builder builder) {
                    super(builder);
                    this.type = builder.type;
                    this.resource = builder.resource;
                    this.label = builder.label;
                    this.description = builder.description;
                    this.accept = builder.accept;
                    this.contentType = builder.contentType;
                    this.destination = builder.destination;
                    this.encodeRequestUrl = ValidationSupport.requireNonNull(builder.encodeRequestUrl, "encodeRequestUrl");
                    this.method = builder.method;
                    this.origin = builder.origin;
                    this.params = builder.params;
                    this.requestHeader = builder.requestHeader;
                    this.requestId = builder.requestId;
                    this.responseId = builder.responseId;
                    this.sourceId = builder.sourceId;
                    this.targetId = builder.targetId;
                    this.url = builder.url;
                }

                /**
                 * <p>
                 * Server interaction or operation type.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Coding}.
                 */
                public Coding getType() {
                    return type;
                }

                /**
                 * <p>
                 * The type of the resource. See http://build.fhir.org/resourcelist.html.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link FHIRDefinedType}.
                 */
                public FHIRDefinedType getResource() {
                    return resource;
                }

                /**
                 * <p>
                 * The label would be used for tracking/logging purposes by test engines.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getLabel() {
                    return label;
                }

                /**
                 * <p>
                 * The description would be used by test engines for tracking and reporting purposes.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getDescription() {
                    return description;
                }

                /**
                 * <p>
                 * The mime-type to use for RESTful operation in the 'Accept' header.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Code}.
                 */
                public Code getAccept() {
                    return accept;
                }

                /**
                 * <p>
                 * The mime-type to use for RESTful operation in the 'Content-Type' header.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Code}.
                 */
                public Code getContentType() {
                    return contentType;
                }

                /**
                 * <p>
                 * The server where the request message is destined for. Must be one of the server numbers listed in TestScript.
                 * destination section.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Integer}.
                 */
                public Integer getDestination() {
                    return destination;
                }

                /**
                 * <p>
                 * Whether or not to implicitly send the request url in encoded format. The default is true to match the standard RESTful 
                 * client behavior. Set to false when communicating with a server that does not support encoded url paths.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Boolean}.
                 */
                public Boolean getEncodeRequestUrl() {
                    return encodeRequestUrl;
                }

                /**
                 * <p>
                 * The HTTP method the test engine MUST use for this operation regardless of any other operation details.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link TestScriptRequestMethodCode}.
                 */
                public TestScriptRequestMethodCode getMethod() {
                    return method;
                }

                /**
                 * <p>
                 * The server where the request message originates from. Must be one of the server numbers listed in TestScript.origin 
                 * section.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Integer}.
                 */
                public Integer getOrigin() {
                    return origin;
                }

                /**
                 * <p>
                 * Path plus parameters after [type]. Used to set parts of the request URL explicitly.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getParams() {
                    return params;
                }

                /**
                 * <p>
                 * Header elements would be used to set HTTP headers.
                 * </p>
                 * 
                 * @return
                 *     A list containing immutable objects of type {@link RequestHeader}.
                 */
                public List<RequestHeader> getRequestHeader() {
                    return requestHeader;
                }

                /**
                 * <p>
                 * The fixture id (maybe new) to map to the request.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Id}.
                 */
                public Id getRequestId() {
                    return requestId;
                }

                /**
                 * <p>
                 * The fixture id (maybe new) to map to the response.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Id}.
                 */
                public Id getResponseId() {
                    return responseId;
                }

                /**
                 * <p>
                 * The id of the fixture used as the body of a PUT or POST request.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Id}.
                 */
                public Id getSourceId() {
                    return sourceId;
                }

                /**
                 * <p>
                 * Id of fixture used for extracting the [id], [type], and [vid] for GET requests.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Id}.
                 */
                public Id getTargetId() {
                    return targetId;
                }

                /**
                 * <p>
                 * Complete request URL.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getUrl() {
                    return url;
                }

                @Override
                public void accept(java.lang.String elementName, Visitor visitor) {
                    if (visitor.preVisit(this)) {
                        visitor.visitStart(elementName, this);
                        if (visitor.visit(elementName, this)) {
                            // visit children
                            accept(id, "id", visitor);
                            accept(extension, "extension", visitor, Extension.class);
                            accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                            accept(type, "type", visitor);
                            accept(resource, "resource", visitor);
                            accept(label, "label", visitor);
                            accept(description, "description", visitor);
                            accept(accept, "accept", visitor);
                            accept(contentType, "contentType", visitor);
                            accept(destination, "destination", visitor);
                            accept(encodeRequestUrl, "encodeRequestUrl", visitor);
                            accept(method, "method", visitor);
                            accept(origin, "origin", visitor);
                            accept(params, "params", visitor);
                            accept(requestHeader, "requestHeader", visitor, RequestHeader.class);
                            accept(requestId, "requestId", visitor);
                            accept(responseId, "responseId", visitor);
                            accept(sourceId, "sourceId", visitor);
                            accept(targetId, "targetId", visitor);
                            accept(url, "url", visitor);
                        }
                        visitor.visitEnd(elementName, this);
                        visitor.postVisit(this);
                    }
                }

                @Override
                public Builder toBuilder() {
                    return Builder.from(this);
                }

                public static Builder builder(Boolean encodeRequestUrl) {
                    return new Builder(encodeRequestUrl);
                }

                public static class Builder extends BackboneElement.Builder {
                    // required
                    private final Boolean encodeRequestUrl;

                    // optional
                    private Coding type;
                    private FHIRDefinedType resource;
                    private String label;
                    private String description;
                    private Code accept;
                    private Code contentType;
                    private Integer destination;
                    private TestScriptRequestMethodCode method;
                    private Integer origin;
                    private String params;
                    private List<RequestHeader> requestHeader = new ArrayList<>();
                    private Id requestId;
                    private Id responseId;
                    private Id sourceId;
                    private Id targetId;
                    private String url;

                    private Builder(Boolean encodeRequestUrl) {
                        super();
                        this.encodeRequestUrl = encodeRequestUrl;
                    }

                    /**
                     * <p>
                     * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                     * contain spaces.
                     * </p>
                     * 
                     * @param id
                     *     Unique id for inter-element referencing
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder id(java.lang.String id) {
                        return (Builder) super.id(id);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                     * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                     * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                     * of the definition of the extension.
                     * </p>
                     * 
                     * @param extension
                     *     Additional content defined by implementations
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder extension(Extension... extension) {
                        return (Builder) super.extension(extension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                     * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                     * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                     * of the definition of the extension.
                     * </p>
                     * 
                     * @param extension
                     *     Additional content defined by implementations
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder extension(Collection<Extension> extension) {
                        return (Builder) super.extension(extension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element and that 
                     * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                     * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                     * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                     * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                     * extension. Applications processing a resource are required to check for modifier extensions.
                     * </p>
                     * <p>
                     * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                     * change the meaning of modifierExtension itself).
                     * </p>
                     * 
                     * @param modifierExtension
                     *     Extensions that cannot be ignored even if unrecognized
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder modifierExtension(Extension... modifierExtension) {
                        return (Builder) super.modifierExtension(modifierExtension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element and that 
                     * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                     * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                     * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                     * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                     * extension. Applications processing a resource are required to check for modifier extensions.
                     * </p>
                     * <p>
                     * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                     * change the meaning of modifierExtension itself).
                     * </p>
                     * 
                     * @param modifierExtension
                     *     Extensions that cannot be ignored even if unrecognized
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder modifierExtension(Collection<Extension> modifierExtension) {
                        return (Builder) super.modifierExtension(modifierExtension);
                    }

                    /**
                     * <p>
                     * Server interaction or operation type.
                     * </p>
                     * 
                     * @param type
                     *     The operation code type that will be executed
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder type(Coding type) {
                        this.type = type;
                        return this;
                    }

                    /**
                     * <p>
                     * The type of the resource. See http://build.fhir.org/resourcelist.html.
                     * </p>
                     * 
                     * @param resource
                     *     Resource type
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder resource(FHIRDefinedType resource) {
                        this.resource = resource;
                        return this;
                    }

                    /**
                     * <p>
                     * The label would be used for tracking/logging purposes by test engines.
                     * </p>
                     * 
                     * @param label
                     *     Tracking/logging operation label
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder label(String label) {
                        this.label = label;
                        return this;
                    }

                    /**
                     * <p>
                     * The description would be used by test engines for tracking and reporting purposes.
                     * </p>
                     * 
                     * @param description
                     *     Tracking/reporting operation description
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder description(String description) {
                        this.description = description;
                        return this;
                    }

                    /**
                     * <p>
                     * The mime-type to use for RESTful operation in the 'Accept' header.
                     * </p>
                     * 
                     * @param accept
                     *     Mime type to accept in the payload of the response, with charset etc.
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder accept(Code accept) {
                        this.accept = accept;
                        return this;
                    }

                    /**
                     * <p>
                     * The mime-type to use for RESTful operation in the 'Content-Type' header.
                     * </p>
                     * 
                     * @param contentType
                     *     Mime type of the request payload contents, with charset etc.
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder contentType(Code contentType) {
                        this.contentType = contentType;
                        return this;
                    }

                    /**
                     * <p>
                     * The server where the request message is destined for. Must be one of the server numbers listed in TestScript.
                     * destination section.
                     * </p>
                     * 
                     * @param destination
                     *     Server responding to the request
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder destination(Integer destination) {
                        this.destination = destination;
                        return this;
                    }

                    /**
                     * <p>
                     * The HTTP method the test engine MUST use for this operation regardless of any other operation details.
                     * </p>
                     * 
                     * @param method
                     *     delete | get | options | patch | post | put | head
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder method(TestScriptRequestMethodCode method) {
                        this.method = method;
                        return this;
                    }

                    /**
                     * <p>
                     * The server where the request message originates from. Must be one of the server numbers listed in TestScript.origin 
                     * section.
                     * </p>
                     * 
                     * @param origin
                     *     Server initiating the request
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder origin(Integer origin) {
                        this.origin = origin;
                        return this;
                    }

                    /**
                     * <p>
                     * Path plus parameters after [type]. Used to set parts of the request URL explicitly.
                     * </p>
                     * 
                     * @param params
                     *     Explicitly defined path parameters
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder params(String params) {
                        this.params = params;
                        return this;
                    }

                    /**
                     * <p>
                     * Header elements would be used to set HTTP headers.
                     * </p>
                     * 
                     * @param requestHeader
                     *     Each operation can have one or more header elements
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder requestHeader(RequestHeader... requestHeader) {
                        for (RequestHeader value : requestHeader) {
                            this.requestHeader.add(value);
                        }
                        return this;
                    }

                    /**
                     * <p>
                     * Header elements would be used to set HTTP headers.
                     * </p>
                     * 
                     * @param requestHeader
                     *     Each operation can have one or more header elements
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder requestHeader(Collection<RequestHeader> requestHeader) {
                        this.requestHeader.addAll(requestHeader);
                        return this;
                    }

                    /**
                     * <p>
                     * The fixture id (maybe new) to map to the request.
                     * </p>
                     * 
                     * @param requestId
                     *     Fixture Id of mapped request
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder requestId(Id requestId) {
                        this.requestId = requestId;
                        return this;
                    }

                    /**
                     * <p>
                     * The fixture id (maybe new) to map to the response.
                     * </p>
                     * 
                     * @param responseId
                     *     Fixture Id of mapped response
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder responseId(Id responseId) {
                        this.responseId = responseId;
                        return this;
                    }

                    /**
                     * <p>
                     * The id of the fixture used as the body of a PUT or POST request.
                     * </p>
                     * 
                     * @param sourceId
                     *     Fixture Id of body for PUT and POST requests
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder sourceId(Id sourceId) {
                        this.sourceId = sourceId;
                        return this;
                    }

                    /**
                     * <p>
                     * Id of fixture used for extracting the [id], [type], and [vid] for GET requests.
                     * </p>
                     * 
                     * @param targetId
                     *     Id of fixture used for extracting the [id], [type], and [vid] for GET requests
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder targetId(Id targetId) {
                        this.targetId = targetId;
                        return this;
                    }

                    /**
                     * <p>
                     * Complete request URL.
                     * </p>
                     * 
                     * @param url
                     *     Request URL
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder url(String url) {
                        this.url = url;
                        return this;
                    }

                    @Override
                    public Operation build() {
                        return new Operation(this);
                    }

                    private static Builder from(Operation operation) {
                        Builder builder = new Builder(operation.encodeRequestUrl);
                        builder.id = operation.id;
                        builder.extension.addAll(operation.extension);
                        builder.modifierExtension.addAll(operation.modifierExtension);
                        builder.type = operation.type;
                        builder.resource = operation.resource;
                        builder.label = operation.label;
                        builder.description = operation.description;
                        builder.accept = operation.accept;
                        builder.contentType = operation.contentType;
                        builder.destination = operation.destination;
                        builder.method = operation.method;
                        builder.origin = operation.origin;
                        builder.params = operation.params;
                        builder.requestHeader.addAll(operation.requestHeader);
                        builder.requestId = operation.requestId;
                        builder.responseId = operation.responseId;
                        builder.sourceId = operation.sourceId;
                        builder.targetId = operation.targetId;
                        builder.url = operation.url;
                        return builder;
                    }
                }

                /**
                 * <p>
                 * Header elements would be used to set HTTP headers.
                 * </p>
                 */
                public static class RequestHeader extends BackboneElement {
                    private final String field;
                    private final String value;

                    private RequestHeader(Builder builder) {
                        super(builder);
                        this.field = ValidationSupport.requireNonNull(builder.field, "field");
                        this.value = ValidationSupport.requireNonNull(builder.value, "value");
                    }

                    /**
                     * <p>
                     * The HTTP header field e.g. "Accept".
                     * </p>
                     * 
                     * @return
                     *     An immutable object of type {@link String}.
                     */
                    public String getField() {
                        return field;
                    }

                    /**
                     * <p>
                     * The value of the header e.g. "application/fhir+xml".
                     * </p>
                     * 
                     * @return
                     *     An immutable object of type {@link String}.
                     */
                    public String getValue() {
                        return value;
                    }

                    @Override
                    public void accept(java.lang.String elementName, Visitor visitor) {
                        if (visitor.preVisit(this)) {
                            visitor.visitStart(elementName, this);
                            if (visitor.visit(elementName, this)) {
                                // visit children
                                accept(id, "id", visitor);
                                accept(extension, "extension", visitor, Extension.class);
                                accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                                accept(field, "field", visitor);
                                accept(value, "value", visitor);
                            }
                            visitor.visitEnd(elementName, this);
                            visitor.postVisit(this);
                        }
                    }

                    @Override
                    public Builder toBuilder() {
                        return Builder.from(this);
                    }

                    public static Builder builder(String field, String value) {
                        return new Builder(field, value);
                    }

                    public static class Builder extends BackboneElement.Builder {
                        // required
                        private final String field;
                        private final String value;

                        private Builder(String field, String value) {
                            super();
                            this.field = field;
                            this.value = value;
                        }

                        /**
                         * <p>
                         * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                         * contain spaces.
                         * </p>
                         * 
                         * @param id
                         *     Unique id for inter-element referencing
                         * 
                         * @return
                         *     A reference to this Builder instance.
                         */
                        @Override
                        public Builder id(java.lang.String id) {
                            return (Builder) super.id(id);
                        }

                        /**
                         * <p>
                         * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                         * of the definition of the extension.
                         * </p>
                         * 
                         * @param extension
                         *     Additional content defined by implementations
                         * 
                         * @return
                         *     A reference to this Builder instance.
                         */
                        @Override
                        public Builder extension(Extension... extension) {
                            return (Builder) super.extension(extension);
                        }

                        /**
                         * <p>
                         * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                         * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                         * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                         * of the definition of the extension.
                         * </p>
                         * 
                         * @param extension
                         *     Additional content defined by implementations
                         * 
                         * @return
                         *     A reference to this Builder instance.
                         */
                        @Override
                        public Builder extension(Collection<Extension> extension) {
                            return (Builder) super.extension(extension);
                        }

                        /**
                         * <p>
                         * May be used to represent additional information that is not part of the basic definition of the element and that 
                         * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                         * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                         * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                         * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                         * extension. Applications processing a resource are required to check for modifier extensions.
                         * </p>
                         * <p>
                         * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                         * change the meaning of modifierExtension itself).
                         * </p>
                         * 
                         * @param modifierExtension
                         *     Extensions that cannot be ignored even if unrecognized
                         * 
                         * @return
                         *     A reference to this Builder instance.
                         */
                        @Override
                        public Builder modifierExtension(Extension... modifierExtension) {
                            return (Builder) super.modifierExtension(modifierExtension);
                        }

                        /**
                         * <p>
                         * May be used to represent additional information that is not part of the basic definition of the element and that 
                         * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                         * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                         * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                         * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                         * extension. Applications processing a resource are required to check for modifier extensions.
                         * </p>
                         * <p>
                         * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                         * change the meaning of modifierExtension itself).
                         * </p>
                         * 
                         * @param modifierExtension
                         *     Extensions that cannot be ignored even if unrecognized
                         * 
                         * @return
                         *     A reference to this Builder instance.
                         */
                        @Override
                        public Builder modifierExtension(Collection<Extension> modifierExtension) {
                            return (Builder) super.modifierExtension(modifierExtension);
                        }

                        @Override
                        public RequestHeader build() {
                            return new RequestHeader(this);
                        }

                        private static Builder from(RequestHeader requestHeader) {
                            Builder builder = new Builder(requestHeader.field, requestHeader.value);
                            builder.id = requestHeader.id;
                            builder.extension.addAll(requestHeader.extension);
                            builder.modifierExtension.addAll(requestHeader.modifierExtension);
                            return builder;
                        }
                    }
                }
            }

            /**
             * <p>
             * Evaluates the results of previous operations to determine if the server under test behaves appropriately.
             * </p>
             */
            public static class Assert extends BackboneElement {
                private final String label;
                private final String description;
                private final AssertionDirectionType direction;
                private final String compareToSourceId;
                private final String compareToSourceExpression;
                private final String compareToSourcePath;
                private final Code contentType;
                private final String expression;
                private final String headerField;
                private final String minimumId;
                private final Boolean navigationLinks;
                private final AssertionOperatorType operator;
                private final String path;
                private final TestScriptRequestMethodCode requestMethod;
                private final String requestURL;
                private final FHIRDefinedType resource;
                private final AssertionResponseTypes response;
                private final String responseCode;
                private final Id sourceId;
                private final Id validateProfileId;
                private final String value;
                private final Boolean warningOnly;

                private Assert(Builder builder) {
                    super(builder);
                    this.label = builder.label;
                    this.description = builder.description;
                    this.direction = builder.direction;
                    this.compareToSourceId = builder.compareToSourceId;
                    this.compareToSourceExpression = builder.compareToSourceExpression;
                    this.compareToSourcePath = builder.compareToSourcePath;
                    this.contentType = builder.contentType;
                    this.expression = builder.expression;
                    this.headerField = builder.headerField;
                    this.minimumId = builder.minimumId;
                    this.navigationLinks = builder.navigationLinks;
                    this.operator = builder.operator;
                    this.path = builder.path;
                    this.requestMethod = builder.requestMethod;
                    this.requestURL = builder.requestURL;
                    this.resource = builder.resource;
                    this.response = builder.response;
                    this.responseCode = builder.responseCode;
                    this.sourceId = builder.sourceId;
                    this.validateProfileId = builder.validateProfileId;
                    this.value = builder.value;
                    this.warningOnly = ValidationSupport.requireNonNull(builder.warningOnly, "warningOnly");
                }

                /**
                 * <p>
                 * The label would be used for tracking/logging purposes by test engines.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getLabel() {
                    return label;
                }

                /**
                 * <p>
                 * The description would be used by test engines for tracking and reporting purposes.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getDescription() {
                    return description;
                }

                /**
                 * <p>
                 * The direction to use for the assertion.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link AssertionDirectionType}.
                 */
                public AssertionDirectionType getDirection() {
                    return direction;
                }

                /**
                 * <p>
                 * Id of the source fixture used as the contents to be evaluated by either the "source/expression" or "sourceId/path" 
                 * definition.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getCompareToSourceId() {
                    return compareToSourceId;
                }

                /**
                 * <p>
                 * The FHIRPath expression to evaluate against the source fixture. When compareToSourceId is defined, either 
                 * compareToSourceExpression or compareToSourcePath must be defined, but not both.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getCompareToSourceExpression() {
                    return compareToSourceExpression;
                }

                /**
                 * <p>
                 * XPath or JSONPath expression to evaluate against the source fixture. When compareToSourceId is defined, either 
                 * compareToSourceExpression or compareToSourcePath must be defined, but not both.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getCompareToSourcePath() {
                    return compareToSourcePath;
                }

                /**
                 * <p>
                 * The mime-type contents to compare against the request or response message 'Content-Type' header.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Code}.
                 */
                public Code getContentType() {
                    return contentType;
                }

                /**
                 * <p>
                 * The FHIRPath expression to be evaluated against the request or response message contents - HTTP headers and payload.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getExpression() {
                    return expression;
                }

                /**
                 * <p>
                 * The HTTP header field name e.g. 'Location'.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getHeaderField() {
                    return headerField;
                }

                /**
                 * <p>
                 * The ID of a fixture. Asserts that the response contains at a minimum the fixture specified by minimumId.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getMinimumId() {
                    return minimumId;
                }

                /**
                 * <p>
                 * Whether or not the test execution performs validation on the bundle navigation links.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Boolean}.
                 */
                public Boolean getNavigationLinks() {
                    return navigationLinks;
                }

                /**
                 * <p>
                 * The operator type defines the conditional behavior of the assert. If not defined, the default is equals.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link AssertionOperatorType}.
                 */
                public AssertionOperatorType getOperator() {
                    return operator;
                }

                /**
                 * <p>
                 * The XPath or JSONPath expression to be evaluated against the fixture representing the response received from server.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getPath() {
                    return path;
                }

                /**
                 * <p>
                 * The request method or HTTP operation code to compare against that used by the client system under test.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link TestScriptRequestMethodCode}.
                 */
                public TestScriptRequestMethodCode getRequestMethod() {
                    return requestMethod;
                }

                /**
                 * <p>
                 * The value to use in a comparison against the request URL path string.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getRequestURL() {
                    return requestURL;
                }

                /**
                 * <p>
                 * The type of the resource. See http://build.fhir.org/resourcelist.html.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link FHIRDefinedType}.
                 */
                public FHIRDefinedType getResource() {
                    return resource;
                }

                /**
                 * <p>
                 * okay | created | noContent | notModified | bad | forbidden | notFound | methodNotAllowed | conflict | gone | 
                 * preconditionFailed | unprocessable.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link AssertionResponseTypes}.
                 */
                public AssertionResponseTypes getResponse() {
                    return response;
                }

                /**
                 * <p>
                 * The value of the HTTP response code to be tested.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getResponseCode() {
                    return responseCode;
                }

                /**
                 * <p>
                 * Fixture to evaluate the XPath/JSONPath expression or the headerField against.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Id}.
                 */
                public Id getSourceId() {
                    return sourceId;
                }

                /**
                 * <p>
                 * The ID of the Profile to validate against.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Id}.
                 */
                public Id getValidateProfileId() {
                    return validateProfileId;
                }

                /**
                 * <p>
                 * The value to compare to.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link String}.
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * <p>
                 * Whether or not the test execution will produce a warning only on error for this assert.
                 * </p>
                 * 
                 * @return
                 *     An immutable object of type {@link Boolean}.
                 */
                public Boolean getWarningOnly() {
                    return warningOnly;
                }

                @Override
                public void accept(java.lang.String elementName, Visitor visitor) {
                    if (visitor.preVisit(this)) {
                        visitor.visitStart(elementName, this);
                        if (visitor.visit(elementName, this)) {
                            // visit children
                            accept(id, "id", visitor);
                            accept(extension, "extension", visitor, Extension.class);
                            accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                            accept(label, "label", visitor);
                            accept(description, "description", visitor);
                            accept(direction, "direction", visitor);
                            accept(compareToSourceId, "compareToSourceId", visitor);
                            accept(compareToSourceExpression, "compareToSourceExpression", visitor);
                            accept(compareToSourcePath, "compareToSourcePath", visitor);
                            accept(contentType, "contentType", visitor);
                            accept(expression, "expression", visitor);
                            accept(headerField, "headerField", visitor);
                            accept(minimumId, "minimumId", visitor);
                            accept(navigationLinks, "navigationLinks", visitor);
                            accept(operator, "operator", visitor);
                            accept(path, "path", visitor);
                            accept(requestMethod, "requestMethod", visitor);
                            accept(requestURL, "requestURL", visitor);
                            accept(resource, "resource", visitor);
                            accept(response, "response", visitor);
                            accept(responseCode, "responseCode", visitor);
                            accept(sourceId, "sourceId", visitor);
                            accept(validateProfileId, "validateProfileId", visitor);
                            accept(value, "value", visitor);
                            accept(warningOnly, "warningOnly", visitor);
                        }
                        visitor.visitEnd(elementName, this);
                        visitor.postVisit(this);
                    }
                }

                @Override
                public Builder toBuilder() {
                    return Builder.from(this);
                }

                public static Builder builder(Boolean warningOnly) {
                    return new Builder(warningOnly);
                }

                public static class Builder extends BackboneElement.Builder {
                    // required
                    private final Boolean warningOnly;

                    // optional
                    private String label;
                    private String description;
                    private AssertionDirectionType direction;
                    private String compareToSourceId;
                    private String compareToSourceExpression;
                    private String compareToSourcePath;
                    private Code contentType;
                    private String expression;
                    private String headerField;
                    private String minimumId;
                    private Boolean navigationLinks;
                    private AssertionOperatorType operator;
                    private String path;
                    private TestScriptRequestMethodCode requestMethod;
                    private String requestURL;
                    private FHIRDefinedType resource;
                    private AssertionResponseTypes response;
                    private String responseCode;
                    private Id sourceId;
                    private Id validateProfileId;
                    private String value;

                    private Builder(Boolean warningOnly) {
                        super();
                        this.warningOnly = warningOnly;
                    }

                    /**
                     * <p>
                     * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                     * contain spaces.
                     * </p>
                     * 
                     * @param id
                     *     Unique id for inter-element referencing
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder id(java.lang.String id) {
                        return (Builder) super.id(id);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                     * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                     * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                     * of the definition of the extension.
                     * </p>
                     * 
                     * @param extension
                     *     Additional content defined by implementations
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder extension(Extension... extension) {
                        return (Builder) super.extension(extension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                     * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                     * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                     * of the definition of the extension.
                     * </p>
                     * 
                     * @param extension
                     *     Additional content defined by implementations
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder extension(Collection<Extension> extension) {
                        return (Builder) super.extension(extension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element and that 
                     * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                     * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                     * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                     * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                     * extension. Applications processing a resource are required to check for modifier extensions.
                     * </p>
                     * <p>
                     * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                     * change the meaning of modifierExtension itself).
                     * </p>
                     * 
                     * @param modifierExtension
                     *     Extensions that cannot be ignored even if unrecognized
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder modifierExtension(Extension... modifierExtension) {
                        return (Builder) super.modifierExtension(modifierExtension);
                    }

                    /**
                     * <p>
                     * May be used to represent additional information that is not part of the basic definition of the element and that 
                     * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                     * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                     * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                     * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                     * extension. Applications processing a resource are required to check for modifier extensions.
                     * </p>
                     * <p>
                     * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                     * change the meaning of modifierExtension itself).
                     * </p>
                     * 
                     * @param modifierExtension
                     *     Extensions that cannot be ignored even if unrecognized
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    @Override
                    public Builder modifierExtension(Collection<Extension> modifierExtension) {
                        return (Builder) super.modifierExtension(modifierExtension);
                    }

                    /**
                     * <p>
                     * The label would be used for tracking/logging purposes by test engines.
                     * </p>
                     * 
                     * @param label
                     *     Tracking/logging assertion label
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder label(String label) {
                        this.label = label;
                        return this;
                    }

                    /**
                     * <p>
                     * The description would be used by test engines for tracking and reporting purposes.
                     * </p>
                     * 
                     * @param description
                     *     Tracking/reporting assertion description
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder description(String description) {
                        this.description = description;
                        return this;
                    }

                    /**
                     * <p>
                     * The direction to use for the assertion.
                     * </p>
                     * 
                     * @param direction
                     *     response | request
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder direction(AssertionDirectionType direction) {
                        this.direction = direction;
                        return this;
                    }

                    /**
                     * <p>
                     * Id of the source fixture used as the contents to be evaluated by either the "source/expression" or "sourceId/path" 
                     * definition.
                     * </p>
                     * 
                     * @param compareToSourceId
                     *     Id of the source fixture to be evaluated
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder compareToSourceId(String compareToSourceId) {
                        this.compareToSourceId = compareToSourceId;
                        return this;
                    }

                    /**
                     * <p>
                     * The FHIRPath expression to evaluate against the source fixture. When compareToSourceId is defined, either 
                     * compareToSourceExpression or compareToSourcePath must be defined, but not both.
                     * </p>
                     * 
                     * @param compareToSourceExpression
                     *     The FHIRPath expression to evaluate against the source fixture
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder compareToSourceExpression(String compareToSourceExpression) {
                        this.compareToSourceExpression = compareToSourceExpression;
                        return this;
                    }

                    /**
                     * <p>
                     * XPath or JSONPath expression to evaluate against the source fixture. When compareToSourceId is defined, either 
                     * compareToSourceExpression or compareToSourcePath must be defined, but not both.
                     * </p>
                     * 
                     * @param compareToSourcePath
                     *     XPath or JSONPath expression to evaluate against the source fixture
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder compareToSourcePath(String compareToSourcePath) {
                        this.compareToSourcePath = compareToSourcePath;
                        return this;
                    }

                    /**
                     * <p>
                     * The mime-type contents to compare against the request or response message 'Content-Type' header.
                     * </p>
                     * 
                     * @param contentType
                     *     Mime type to compare against the 'Content-Type' header
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder contentType(Code contentType) {
                        this.contentType = contentType;
                        return this;
                    }

                    /**
                     * <p>
                     * The FHIRPath expression to be evaluated against the request or response message contents - HTTP headers and payload.
                     * </p>
                     * 
                     * @param expression
                     *     The FHIRPath expression to be evaluated
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder expression(String expression) {
                        this.expression = expression;
                        return this;
                    }

                    /**
                     * <p>
                     * The HTTP header field name e.g. 'Location'.
                     * </p>
                     * 
                     * @param headerField
                     *     HTTP header field name
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder headerField(String headerField) {
                        this.headerField = headerField;
                        return this;
                    }

                    /**
                     * <p>
                     * The ID of a fixture. Asserts that the response contains at a minimum the fixture specified by minimumId.
                     * </p>
                     * 
                     * @param minimumId
                     *     Fixture Id of minimum content resource
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder minimumId(String minimumId) {
                        this.minimumId = minimumId;
                        return this;
                    }

                    /**
                     * <p>
                     * Whether or not the test execution performs validation on the bundle navigation links.
                     * </p>
                     * 
                     * @param navigationLinks
                     *     Perform validation on navigation links?
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder navigationLinks(Boolean navigationLinks) {
                        this.navigationLinks = navigationLinks;
                        return this;
                    }

                    /**
                     * <p>
                     * The operator type defines the conditional behavior of the assert. If not defined, the default is equals.
                     * </p>
                     * 
                     * @param operator
                     *     equals | notEquals | in | notIn | greaterThan | lessThan | empty | notEmpty | contains | notContains | eval
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder operator(AssertionOperatorType operator) {
                        this.operator = operator;
                        return this;
                    }

                    /**
                     * <p>
                     * The XPath or JSONPath expression to be evaluated against the fixture representing the response received from server.
                     * </p>
                     * 
                     * @param path
                     *     XPath or JSONPath expression
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder path(String path) {
                        this.path = path;
                        return this;
                    }

                    /**
                     * <p>
                     * The request method or HTTP operation code to compare against that used by the client system under test.
                     * </p>
                     * 
                     * @param requestMethod
                     *     delete | get | options | patch | post | put | head
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder requestMethod(TestScriptRequestMethodCode requestMethod) {
                        this.requestMethod = requestMethod;
                        return this;
                    }

                    /**
                     * <p>
                     * The value to use in a comparison against the request URL path string.
                     * </p>
                     * 
                     * @param requestURL
                     *     Request URL comparison value
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder requestURL(String requestURL) {
                        this.requestURL = requestURL;
                        return this;
                    }

                    /**
                     * <p>
                     * The type of the resource. See http://build.fhir.org/resourcelist.html.
                     * </p>
                     * 
                     * @param resource
                     *     Resource type
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder resource(FHIRDefinedType resource) {
                        this.resource = resource;
                        return this;
                    }

                    /**
                     * <p>
                     * okay | created | noContent | notModified | bad | forbidden | notFound | methodNotAllowed | conflict | gone | 
                     * preconditionFailed | unprocessable.
                     * </p>
                     * 
                     * @param response
                     *     okay | created | noContent | notModified | bad | forbidden | notFound | methodNotAllowed | conflict | gone | 
                     *     preconditionFailed | unprocessable
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder response(AssertionResponseTypes response) {
                        this.response = response;
                        return this;
                    }

                    /**
                     * <p>
                     * The value of the HTTP response code to be tested.
                     * </p>
                     * 
                     * @param responseCode
                     *     HTTP response code to test
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder responseCode(String responseCode) {
                        this.responseCode = responseCode;
                        return this;
                    }

                    /**
                     * <p>
                     * Fixture to evaluate the XPath/JSONPath expression or the headerField against.
                     * </p>
                     * 
                     * @param sourceId
                     *     Fixture Id of source expression or headerField
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder sourceId(Id sourceId) {
                        this.sourceId = sourceId;
                        return this;
                    }

                    /**
                     * <p>
                     * The ID of the Profile to validate against.
                     * </p>
                     * 
                     * @param validateProfileId
                     *     Profile Id of validation profile reference
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder validateProfileId(Id validateProfileId) {
                        this.validateProfileId = validateProfileId;
                        return this;
                    }

                    /**
                     * <p>
                     * The value to compare to.
                     * </p>
                     * 
                     * @param value
                     *     The value to compare to
                     * 
                     * @return
                     *     A reference to this Builder instance.
                     */
                    public Builder value(String value) {
                        this.value = value;
                        return this;
                    }

                    @Override
                    public Assert build() {
                        return new Assert(this);
                    }

                    private static Builder from(Assert _assert) {
                        Builder builder = new Builder(_assert.warningOnly);
                        builder.id = _assert.id;
                        builder.extension.addAll(_assert.extension);
                        builder.modifierExtension.addAll(_assert.modifierExtension);
                        builder.label = _assert.label;
                        builder.description = _assert.description;
                        builder.direction = _assert.direction;
                        builder.compareToSourceId = _assert.compareToSourceId;
                        builder.compareToSourceExpression = _assert.compareToSourceExpression;
                        builder.compareToSourcePath = _assert.compareToSourcePath;
                        builder.contentType = _assert.contentType;
                        builder.expression = _assert.expression;
                        builder.headerField = _assert.headerField;
                        builder.minimumId = _assert.minimumId;
                        builder.navigationLinks = _assert.navigationLinks;
                        builder.operator = _assert.operator;
                        builder.path = _assert.path;
                        builder.requestMethod = _assert.requestMethod;
                        builder.requestURL = _assert.requestURL;
                        builder.resource = _assert.resource;
                        builder.response = _assert.response;
                        builder.responseCode = _assert.responseCode;
                        builder.sourceId = _assert.sourceId;
                        builder.validateProfileId = _assert.validateProfileId;
                        builder.value = _assert.value;
                        return builder;
                    }
                }
            }
        }
    }

    /**
     * <p>
     * A test in this script.
     * </p>
     */
    public static class Test extends BackboneElement {
        private final String name;
        private final String description;
        private final List<Action> action;

        private Test(Builder builder) {
            super(builder);
            this.name = builder.name;
            this.description = builder.description;
            this.action = ValidationSupport.requireNonEmpty(builder.action, "action");
        }

        /**
         * <p>
         * The name of this test used for tracking/logging purposes by test engines.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getName() {
            return name;
        }

        /**
         * <p>
         * A short description of the test used by test engines for tracking and reporting purposes.
         * </p>
         * 
         * @return
         *     An immutable object of type {@link String}.
         */
        public String getDescription() {
            return description;
        }

        /**
         * <p>
         * Action would contain either an operation or an assertion.
         * </p>
         * 
         * @return
         *     A list containing immutable objects of type {@link Action}.
         */
        public List<Action> getAction() {
            return action;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(name, "name", visitor);
                    accept(description, "description", visitor);
                    accept(action, "action", visitor, Action.class);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return Builder.from(this);
        }

        public static Builder builder(List<Action> action) {
            return new Builder(action);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final List<Action> action;

            // optional
            private String name;
            private String description;

            private Builder(List<Action> action) {
                super();
                this.action = action;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * The name of this test used for tracking/logging purposes by test engines.
             * </p>
             * 
             * @param name
             *     Tracking/logging name of this test
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder name(String name) {
                this.name = name;
                return this;
            }

            /**
             * <p>
             * A short description of the test used by test engines for tracking and reporting purposes.
             * </p>
             * 
             * @param description
             *     Tracking/reporting short description of the test
             * 
             * @return
             *     A reference to this Builder instance.
             */
            public Builder description(String description) {
                this.description = description;
                return this;
            }

            @Override
            public Test build() {
                return new Test(this);
            }

            private static Builder from(Test test) {
                Builder builder = new Builder(test.action);
                builder.id = test.id;
                builder.extension.addAll(test.extension);
                builder.modifierExtension.addAll(test.modifierExtension);
                builder.name = test.name;
                builder.description = test.description;
                return builder;
            }
        }

        /**
         * <p>
         * Action would contain either an operation or an assertion.
         * </p>
         */
        public static class Action extends BackboneElement {
            private final TestScript.Setup.Action.Operation operation;
            private final TestScript.Setup.Action.Assert _assert;

            private Action(Builder builder) {
                super(builder);
                this.operation = builder.operation;
                this._assert = builder._assert;
            }

            /**
             * <p>
             * An operation would involve a REST request to a server.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Operation}.
             */
            public TestScript.Setup.Action.Operation getOperation() {
                return operation;
            }

            /**
             * <p>
             * Evaluates the results of previous operations to determine if the server under test behaves appropriately.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Assert}.
             */
            public TestScript.Setup.Action.Assert getassert() {
                return _assert;
            }

            @Override
            public void accept(java.lang.String elementName, Visitor visitor) {
                if (visitor.preVisit(this)) {
                    visitor.visitStart(elementName, this);
                    if (visitor.visit(elementName, this)) {
                        // visit children
                        accept(id, "id", visitor);
                        accept(extension, "extension", visitor, Extension.class);
                        accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                        accept(operation, "operation", visitor);
                        accept(_assert, "assert", visitor);
                    }
                    visitor.visitEnd(elementName, this);
                    visitor.postVisit(this);
                }
            }

            @Override
            public Builder toBuilder() {
                return Builder.from(this);
            }

            public static Builder builder() {
                return new Builder();
            }

            public static class Builder extends BackboneElement.Builder {
                // optional
                private TestScript.Setup.Action.Operation operation;
                private TestScript.Setup.Action.Assert _assert;

                private Builder() {
                    super();
                }

                /**
                 * <p>
                 * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                 * contain spaces.
                 * </p>
                 * 
                 * @param id
                 *     Unique id for inter-element referencing
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder id(java.lang.String id) {
                    return (Builder) super.id(id);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Extension... extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Collection<Extension> extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Extension... modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Collection<Extension> modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * An operation would involve a REST request to a server.
                 * </p>
                 * 
                 * @param operation
                 *     The setup operation to perform
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder operation(TestScript.Setup.Action.Operation operation) {
                    this.operation = operation;
                    return this;
                }

                /**
                 * <p>
                 * Evaluates the results of previous operations to determine if the server under test behaves appropriately.
                 * </p>
                 * 
                 * @param _assert
                 *     The setup assertion to perform
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                public Builder _assert(TestScript.Setup.Action.Assert _assert) {
                    this._assert = _assert;
                    return this;
                }

                @Override
                public Action build() {
                    return new Action(this);
                }

                private static Builder from(Action action) {
                    Builder builder = new Builder();
                    builder.id = action.id;
                    builder.extension.addAll(action.extension);
                    builder.modifierExtension.addAll(action.modifierExtension);
                    builder.operation = action.operation;
                    builder._assert = action._assert;
                    return builder;
                }
            }
        }
    }

    /**
     * <p>
     * A series of operations required to clean up after all the tests are executed (successfully or otherwise).
     * </p>
     */
    public static class Teardown extends BackboneElement {
        private final List<Action> action;

        private Teardown(Builder builder) {
            super(builder);
            this.action = ValidationSupport.requireNonEmpty(builder.action, "action");
        }

        /**
         * <p>
         * The teardown action will only contain an operation.
         * </p>
         * 
         * @return
         *     A list containing immutable objects of type {@link Action}.
         */
        public List<Action> getAction() {
            return action;
        }

        @Override
        public void accept(java.lang.String elementName, Visitor visitor) {
            if (visitor.preVisit(this)) {
                visitor.visitStart(elementName, this);
                if (visitor.visit(elementName, this)) {
                    // visit children
                    accept(id, "id", visitor);
                    accept(extension, "extension", visitor, Extension.class);
                    accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                    accept(action, "action", visitor, Action.class);
                }
                visitor.visitEnd(elementName, this);
                visitor.postVisit(this);
            }
        }

        @Override
        public Builder toBuilder() {
            return Builder.from(this);
        }

        public static Builder builder(List<Action> action) {
            return new Builder(action);
        }

        public static class Builder extends BackboneElement.Builder {
            // required
            private final List<Action> action;

            private Builder(List<Action> action) {
                super();
                this.action = action;
            }

            /**
             * <p>
             * Unique id for the element within a resource (for internal references). This may be any string value that does not 
             * contain spaces.
             * </p>
             * 
             * @param id
             *     Unique id for inter-element referencing
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder id(java.lang.String id) {
                return (Builder) super.id(id);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Extension... extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element. To make the 
             * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
             * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
             * of the definition of the extension.
             * </p>
             * 
             * @param extension
             *     Additional content defined by implementations
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder extension(Collection<Extension> extension) {
                return (Builder) super.extension(extension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Extension... modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            /**
             * <p>
             * May be used to represent additional information that is not part of the basic definition of the element and that 
             * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
             * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
             * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
             * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
             * extension. Applications processing a resource are required to check for modifier extensions.
             * </p>
             * <p>
             * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
             * change the meaning of modifierExtension itself).
             * </p>
             * 
             * @param modifierExtension
             *     Extensions that cannot be ignored even if unrecognized
             * 
             * @return
             *     A reference to this Builder instance.
             */
            @Override
            public Builder modifierExtension(Collection<Extension> modifierExtension) {
                return (Builder) super.modifierExtension(modifierExtension);
            }

            @Override
            public Teardown build() {
                return new Teardown(this);
            }

            private static Builder from(Teardown teardown) {
                Builder builder = new Builder(teardown.action);
                builder.id = teardown.id;
                builder.extension.addAll(teardown.extension);
                builder.modifierExtension.addAll(teardown.modifierExtension);
                return builder;
            }
        }

        /**
         * <p>
         * The teardown action will only contain an operation.
         * </p>
         */
        public static class Action extends BackboneElement {
            private final TestScript.Setup.Action.Operation operation;

            private Action(Builder builder) {
                super(builder);
                this.operation = ValidationSupport.requireNonNull(builder.operation, "operation");
            }

            /**
             * <p>
             * An operation would involve a REST request to a server.
             * </p>
             * 
             * @return
             *     An immutable object of type {@link Operation}.
             */
            public TestScript.Setup.Action.Operation getOperation() {
                return operation;
            }

            @Override
            public void accept(java.lang.String elementName, Visitor visitor) {
                if (visitor.preVisit(this)) {
                    visitor.visitStart(elementName, this);
                    if (visitor.visit(elementName, this)) {
                        // visit children
                        accept(id, "id", visitor);
                        accept(extension, "extension", visitor, Extension.class);
                        accept(modifierExtension, "modifierExtension", visitor, Extension.class);
                        accept(operation, "operation", visitor);
                    }
                    visitor.visitEnd(elementName, this);
                    visitor.postVisit(this);
                }
            }

            @Override
            public Builder toBuilder() {
                return Builder.from(this);
            }

            public static Builder builder(TestScript.Setup.Action.Operation operation) {
                return new Builder(operation);
            }

            public static class Builder extends BackboneElement.Builder {
                // required
                private final TestScript.Setup.Action.Operation operation;

                private Builder(TestScript.Setup.Action.Operation operation) {
                    super();
                    this.operation = operation;
                }

                /**
                 * <p>
                 * Unique id for the element within a resource (for internal references). This may be any string value that does not 
                 * contain spaces.
                 * </p>
                 * 
                 * @param id
                 *     Unique id for inter-element referencing
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder id(java.lang.String id) {
                    return (Builder) super.id(id);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Extension... extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element. To make the 
                 * use of extensions safe and manageable, there is a strict set of governance applied to the definition and use of 
                 * extensions. Though any implementer can define an extension, there is a set of requirements that SHALL be met as part 
                 * of the definition of the extension.
                 * </p>
                 * 
                 * @param extension
                 *     Additional content defined by implementations
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder extension(Collection<Extension> extension) {
                    return (Builder) super.extension(extension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Extension... modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                /**
                 * <p>
                 * May be used to represent additional information that is not part of the basic definition of the element and that 
                 * modifies the understanding of the element in which it is contained and/or the understanding of the containing 
                 * element's descendants. Usually modifier elements provide negation or qualification. To make the use of extensions safe 
                 * and manageable, there is a strict set of governance applied to the definition and use of extensions. Though any 
                 * implementer can define an extension, there is a set of requirements that SHALL be met as part of the definition of the 
                 * extension. Applications processing a resource are required to check for modifier extensions.
                 * </p>
                 * <p>
                 * Modifier extensions SHALL NOT change the meaning of any elements on Resource or DomainResource (including cannot 
                 * change the meaning of modifierExtension itself).
                 * </p>
                 * 
                 * @param modifierExtension
                 *     Extensions that cannot be ignored even if unrecognized
                 * 
                 * @return
                 *     A reference to this Builder instance.
                 */
                @Override
                public Builder modifierExtension(Collection<Extension> modifierExtension) {
                    return (Builder) super.modifierExtension(modifierExtension);
                }

                @Override
                public Action build() {
                    return new Action(this);
                }

                private static Builder from(Action action) {
                    Builder builder = new Builder(action.operation);
                    builder.id = action.id;
                    builder.extension.addAll(action.extension);
                    builder.modifierExtension.addAll(action.modifierExtension);
                    return builder;
                }
            }
        }
    }
}
