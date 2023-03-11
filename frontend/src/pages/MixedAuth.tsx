import useAuth from "../hooks/useAuth";
import Layout from "../component/Layout";

export default function MixedAuthPage () {
    const user = useAuth(false);

    return (
        <Layout>
            <div>
                <h1>Content for unauthenticated</h1>
                {user && <h1>Content for authenticated only</h1>}
            </div>
        </Layout>
    );
}