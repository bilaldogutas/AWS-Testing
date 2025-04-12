# AWS-Testing 
![Static Badge](https://img.shields.io/badge/license-MIT-crimson)

1. HomePageTests

Tests related to the AWS Free Tier homepage structure and behavior.

testPageTitle – Verify that the homepage title is correct.

testCurrentURL – Confirm that the loaded URL matches the expected.

testHeroImageVisibility – Assert that the main banner or hero image is visible.

testScrollToBottom – Scroll to the bottom and validate visibility of footer.

testScreenshotOfHomePage – Take and save a screenshot of the homepage.

2. NavigationMenuTests

Tests interactions with the top navigation bar and dropdown menus.

testClickPricingLink – Click the "Pricing" nav item and verify redirection.

testHoverOverProducts – Hover over "Products" to trigger dropdown.

testSolutionsLinkOpensCorrectPage – Click "Solutions" and verify destination.

testAllNavLinksAreDisplayed – Ensure all top-level nav links are visible.

testNavLinkTargetBehavior – Check if external links open in new tab.

3. SignupFlowTests

Tests the "Create a Free Account" form interaction.

testNavigateToSignupPage – Click "Create a Free Account" and confirm redirection.

testEmptyFormSubmission – Try to submit the form with no input and verify error messages.

testPartialFormSubmission – Submit form with only name/email and verify warnings.

testValidFormSubmission – Fill all fields with valid data and proceed to next step.

testFormFieldPresence – Assert presence of key input fields (name, email, country, etc.).

4. FooterTests

Tests links and content found in the footer section.

testPrivacyPolicyLink – Click and validate the Privacy Policy page loads.

testTermsOfServiceLink – Test Terms of Service link behavior.

testContactUsLink – Click “Contact Us” and verify it navigates to the right place.

testSocialIconsPresence – Confirm LinkedIn/Twitter/Facebook icons are visible.

testFooterTextContent – Validate expected text is present (like © AWS or email).

5. SearchFunctionTests (if applicable on site)

Tests interactions with any search bar or filter system.

testSearchBarVisible – Check if the search input is present on the page.

testSearchValidQuery – Enter "EC2" or similar, validate results show up.

testSearchNoResultsQuery – Search for gibberish and validate "no results" message.

testClearSearchInput – Type, then clear input; validate that state resets.

testClickOnSearchResult – Click first result and verify destination loads correctly.

6. DropdownAndFilterTests

Tests dropdowns and filters for Free Tier services.

testCategoryDropdownVisible – Ensure the category filter dropdown is shown.

testSelectComputeCategory – Select a specific category like “Compute.”

testSortByDefaultOption – Check that default sort option is selected initially.

testChangeSortOrder – Change sort to another order and validate results.

testFilterPersistence – Apply a filter, reload page, check if it sticks (if applicable).

7. FormElementTests

test page: https://pages.awscloud.com/Amazon-Elastic-VMware-Service-EVS-preview.html

testRadioButtonSelection – Select and verify “Personal” vs. “Business” radio options.

testRequiredNameField -

testRequiredEmailField -

testCompleteFormSubmission -

testNavigateAfterSubmission -


8. WindowAndAlertTests

test page: https://aws.amazon.com/partners/capgemini/

testExternalLinkOpensNewTab – Click a link that opens in a new tab and validate tab switch. +

testSwitchBackToMainTab – Return to the original tab and verify it's still responsive. +

testModalPopupDisplay – Trigger a modal and confirm visibility.

testAcceptAlertBox – Handle a JS alert/confirmation (if present).

testMultipleTabNavigation – Open and switch between multiple tabs. +

