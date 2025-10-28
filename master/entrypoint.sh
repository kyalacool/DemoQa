set -e

echo "--- Dynamic GID synchronization start ---"

SOCKET_PATH="/var/run/docker.sock"

if; then
    echo "Error: The $SOCKET_PATH not found."
    DOCKER_GID=""
else
    DOCKER_GID=$(stat -c '%g' "$SOCKET_PATH")

    echo "Hoszt Docker GID: $DOCKER_GID"

    if getent group docker ; then
        groupdel docker
    fi

    groupadd -g "$DOCKER_GID" docker

    usermod -aG docker jenkins

    echo "The 'jenkins' user added to the 'docker' group (GID: $DOCKER_GID)."
fi

echo "--- Jenkins start...  ---"

exec gosu jenkins /usr/bin/tini -- /usr/local/bin/jenkins.shsr/local/bin/jenkins.sh