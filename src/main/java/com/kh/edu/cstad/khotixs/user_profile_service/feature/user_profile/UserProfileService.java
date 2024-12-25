package com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile;

import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileResponse;
import com.kh.edu.cstad.khotixs.user_profile_service.feature.user_profile.dto.UserProfileUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

/**
 * This interface use for manage user profile
 *
 * @author Phal Seakngim
 * @since 2024
 */
public interface UserProfileService {

    /**
     * get profile
     * @param authentication is the spring object that contain information user
     * @return {@link UserProfileResponse}
     */
    UserProfileResponse findMe(Authentication authentication);


    /**
     * Get User By Email
     *
     * @param email is the email use to find user
     * @return {@link UserProfileResponse}
     * @author Phal Seakngim
     * @since 2024
     */
    UserProfileResponse getUserByEmail(String email);

    /**
     * Retrieve all users with pagination.
     *
     * @param pageNumber the page number to retrieve (zero-based index)
     * @param pageSize   the number of users per page
     * @return {@link Page<UserProfileResponse>}
     * @author Phal Seakngim
     * @since 2024
     */
    Page<UserProfileResponse> getAllUsers(
            int pageNumber,
            int pageSize
    );

    /**
     * Enables a user based on their email.
     *
     * @param email the email of the user to be enabled
     * @author Phal Seakngim
     * @since 2024
     */
    void enableUser(String email);

    /**
     * Disables a user based on their email.
     *
     * @param email the email of the user to be disabled
     * @author Phal Seakngim
     * @since 2024
     */
    void disableUser(String email);

    /**
     * Updates a user's profile based on their email and the provided update request.
     *
     * @param email                    the email of the user to be updated
     * @param userProfileUpdateRequest the request containing the new user profile information
     * @return {@link UserProfileResponse}
     * @author Phal Seakngim
     * @since 2024
     */
    UserProfileResponse updateUser(String email, UserProfileUpdateRequest userProfileUpdateRequest);

}
